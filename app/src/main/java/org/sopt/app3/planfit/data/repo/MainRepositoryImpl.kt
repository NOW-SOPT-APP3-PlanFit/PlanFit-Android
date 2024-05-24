package org.sopt.app3.planfit.data.repo

import org.sopt.app3.planfit.data.api.MainService
import org.sopt.app3.planfit.data.model.request.RequestPutMainDto
import org.sopt.app3.planfit.data.model.response.toDomain
import org.sopt.app3.planfit.domain.model.MainGet
import org.sopt.app3.planfit.domain.model.MainPut
import org.sopt.app3.planfit.domain.repo.MainRepository

class MainRepositoryImpl(
    private val mainService: MainService,
) : MainRepository {

    override suspend fun getMain(): Result<MainGet> =
        runCatching {
            mainService.getMain().data!!.toDomain()
        }

    override suspend fun putMain(mainPut: MainPut): Result<Unit> =
        runCatching {
            val requestDto = RequestPutMainDto(
                minute = mainPut.minute,
                condition = mainPut.condition
            )
            mainService.putMain(requestDto)
        }
}