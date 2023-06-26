package storage;
import java.util.Collection;

import data.*;

public abstract class Database {

    /**
     * Добавляет группу в базу, не меняя её id
     * @param newGroup группа, которую нужно добавить в коллекцию
     * @return true, если группа успешно добавлена в базу, false, если нет
     */
    public abstract void add(StudyGroup newGroup);

    /**
     * Удаляет группу из базы по её id
     * @param id ИД группы
     * @throws GroupDidNotFound если группа с указанным id не найдена
     * @return true, если группа удалена, false, если нет
     */
    public abstract void remove(long id) throws GroupDidNotFound;

    /**
     * Очищает базу
     */
    public abstract void clear();

    /**
     * Удаляет первую группу
     * @return StudyGroup удаленная группа
     */
    public abstract StudyGroup removeHead() throws GroupDidNotFound;

    /**
     * Обновляет группу по заданому id
     * @param id ИД группы
     * @param group обновленная группа
     * @throws GroupDidNotFound если группа с указанным id не найдена
     */
    public abstract void update(long id, StudyGroup group) throws GroupDidNotFound;

    /**
     * Добавляет элемент в базу, если он больше наибольшего элемента в базе (сортировка по названию группы)
     * @param group добавляемая группа
     */
    public abstract void addIfMax(StudyGroup group);

    /**
     * Возвращает информацию о базе(тип данных, дата создания, размер)
     * @return информация о базе
     */
    public abstract String getInfo();

    /**
     * Возвращает группу, в которой больше всего студентов
     * @return группа
     */
    public abstract StudyGroup getMaxByStudentsCountGroup();

    /**
     * Возвращает список количеств отчисленных студентов из групп
     * @return список количеств отчисленных студентов
     */
    public abstract Collection<Long> getExpelledStudentsCount();

    /**
     * Возвращает имена уникальных админов групп
     * @return список строк - имен админов
     */
    public abstract Collection<String> getUniqueNamesGroupsAdmins();

    /**
     * Выводит все группы
     * @return строка - информация о группах
     */
    public abstract Collection<StudyGroup> showAllGroups();
}