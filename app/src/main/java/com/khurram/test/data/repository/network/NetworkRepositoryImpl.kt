package com.khurram.test.data.repository.network

import com.khurram.test.data.network.APIsInterface
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private var apisInterface: APIsInterface,
) : NetworkRepository {

}