package Conditions;

import org.telegram.telegrambots.api.objects.Update;

public abstract class Condition {
    public abstract void f(Update update);
}
