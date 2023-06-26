package i18n;

import command.NotCorrectLoginOrPassword;

import java.util.ListResourceBundle;

public class GuiLabels extends ListResourceBundle{
    @Override
    protected Object [][] getContents(){
        return contents;
    }

    private Object [][] contents={{"NotCorrectLoginOrPassword","Неверный логин или пароль"},
            {"auth","Авторизация"},
            {"reg","Регистрация"},{"login","Логин"},{"password","Пароль"},
            {"ThereIsUserWithThisLogin","Пользователь с таким логином уже есть"},
            {"PasswordsNotEqual","Пароли не совпадают!"},
            {"RepeatPassword","Повторите пароль"},
            {"ID","ИД"},{"Name","Название"},{"Coordinates","Координаты"},
            {"CreationTime","Время создания"},{"StudentsCount","Количество студентов"},
            {"ExpelledStudentsCount","Количество отчисленных студентов"},
            {"ShouldBeExpelledCount","Количество студентов на ППА"},
            {"Semester","Семестр"},{"Admin","Админ"},{"Table","Таблица"},
            {"Map","Карта"},{"add","Добавить"},{"addIfMax","Добавить, если максимальный"}, {"remove","Удалить"},
            {"clear","Очистить"},{"execute","Выполнить скрипт"},{"info","Информация"},
            {"FillGraphs","Заполните все обязательные поля"}, {"First","Первый"},
            {"Third","Третий"},{"Eight","Восьмой"}, {"Red","Красный"},{"Blue","Синий"},{"Brown","Коричневый"},
            {"Green","Зеленый"},{"Orange","Оранжевый"},{"EyeColor","Цвет глаз"},{"HairColor","Цвет волос"},
            {"PersonName","Имя"},{"Weight","Вес"},{"Nationality","Национальность"},
            {"NorthKorea","Северная Корея"}, {"SouthKorea","Южная Корея"}, {"UnitedKingdom","Великобритания"},
            {"NoRights","Нет прав на изменение"},{"Update","Обновить"}, {"ChooseARow","Выберите строку!"},
            {"AreYouSure","Вы уверены?"},{"Yes","Да"},{"No","Нет"},
            {"ErrorDuringExecution","Ошибка во время исполнения скрипта"},
            {"ChooseAGroup","Выберите группу"},{"filter","Фильтр"}
    };
}
