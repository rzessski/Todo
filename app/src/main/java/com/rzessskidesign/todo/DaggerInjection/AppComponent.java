package com.rzessskidesign.todo.DaggerInjection;

import com.rzessskidesign.todo.MainActivity;
import com.rzessskidesign.todo.ui.LoginRegister.LogReg;
import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(MainActivity mainActivity);
    void inject(LogReg logReg);
}
