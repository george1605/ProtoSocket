package com.example.protosocket

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface PacketRepository : MongoRepository<PacketEntity, String> {

}