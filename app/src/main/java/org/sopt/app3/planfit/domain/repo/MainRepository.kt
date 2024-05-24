package org.sopt.app3.planfit.domain.repo

import org.sopt.app3.planfit.domain.model.MainGet
import org.sopt.app3.planfit.domain.model.MainPut

interface MainRepository {
    suspend fun getMain() : Result<List<MainGet>>
    suspend fun putMain(mainPut: MainPut) : Result<Unit>

}