package be.nck

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.Duration
import java.time.LocalTime

class RequestLoop {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            start()
        }

        fun start() {
            val health = URI.create("http://test-nick-pieter-555587170.eu-west-1.elb.amazonaws.com/health")
            val apiCall = URI.create("http://test-nick-pieter-555587170.eu-west-1.elb.amazonaws.com/api/belgium/dender/viaRoute")
            val local = URI.create("http://localhost:8081/health")
            System.setProperty("jdk.httpclient.keepalive.timeout", "99999")
            val request = HttpRequest.newBuilder().GET().uri(local).build()
            val putRequest = HttpRequest.newBuilder().PUT(HttpRequest.BodyPublishers.ofString("")).uri(local).build()
            val httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofMinutes(5)).build()


            while (true) {
                val now = LocalTime.now()
                val response = httpClient.send(putRequest, HttpResponse.BodyHandlers.ofString())
                println("$now: $response")
                Thread.sleep(5_050)
            }
        }
    }
}
