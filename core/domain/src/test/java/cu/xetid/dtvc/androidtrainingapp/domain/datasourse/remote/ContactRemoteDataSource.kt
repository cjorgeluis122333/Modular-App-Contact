package com.example.contactnotmodular.core.domain.datasourse.remote

import com.example.contactnotmodular.core.model.ResultValue
import cu.xetid.dtvc.androidtrainingapp.domain.datasourse.remote.RemoteDataSource
import cu.xetid.dtvc.androidtrainingapp.model.projection.ContactLocationProjection
import cu.xetid.dtvc.androidtrainingapp.model.projection.ContactNameProjection
import cu.xetid.dtvc.androidtrainingapp.model.projection.ContactPictureProjection

interface ContactRemoteDataSource: RemoteDataSource {

    suspend fun getUserName(): ResultValue<ContactNameProjection>

    suspend fun getUserLocation(): ResultValue<ContactLocationProjection>

    suspend fun getUserPicture(): ResultValue<ContactPictureProjection>

}