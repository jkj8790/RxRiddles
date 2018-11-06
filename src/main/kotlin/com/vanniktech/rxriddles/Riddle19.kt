package com.vanniktech.rxriddles

import io.reactivex.Observable

object Riddle19 {
  /**
   * Use the given [Interaction] interface and use its listener to transform the [Int] callback to an Observable that emits every time the listener is called.
   * Int 콜백을 리스너가 호출될때마다 emit 하는 Observable로 바꾸십쇼
   * When the Observable is being disposed the listener should be set to null.
   *
   * Use case: Transform any listener into an Observable.
   */
  fun solve(interaction: Interaction): Observable<Int> {
    return Observable.create<Int> { emitter ->
      interaction.listener = {
        if(!emitter.isDisposed) emitter.onNext(it)
      }
    }.doOnDispose { interaction.listener = null }

  }

  interface Interaction {
    var listener: ((Int) -> Unit)?
  }
}
