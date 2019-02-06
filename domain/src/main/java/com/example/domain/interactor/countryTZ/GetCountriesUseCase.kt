package com.example.domain.interactor.countryTZ

import com.example.domain.executor.PostExecutionThread
import com.example.domain.executor.ThreadExecutor
import com.example.domain.interactor.UseCase
import com.example.domain.gateway.CountryGateway
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject



class GetCountriesUseCase @Inject constructor(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    compositeDisposable: CompositeDisposable,
    private val sferaGateway: CountryGateway
) : UseCase<String, GetCountriesUseCase.Params>(threadExecutor, postExecutionThread, compositeDisposable) {

    override fun buildUseCaseObservable(params: Params): Single<String> = sferaGateway.getCountries()

    class Params
}