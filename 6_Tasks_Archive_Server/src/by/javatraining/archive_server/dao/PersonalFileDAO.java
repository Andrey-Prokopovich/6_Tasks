package by.javatraining.archive_server.dao;

import by.javatraining.archive_server.entity.PersonalFile;
import by.javatraining.archive_server.entity.PersonalFileFull;

/*
 * Попробуйте решить данную задачу хотя бы на 50%.
 * 
 * Создайте клиент-серверное приложение "Архив".
 * 
 * Общие требования к заданию:
 * * В архиве хранятся Дела(например, студентов). Архив находится на сервере.
 * * Клиент, в зависимости от прав, может запросить дело на просмотр,
 *   внести в него изменения, или создать новое дело.
 *   
 * Требования к коду лабораторной работы:
 * * Для реализации сетевого соединения используйте сокеты.
 * * Формат хранения данных на сервере - xml-файлы.
 */

public interface PersonalFileDAO {

	long getNextNumber();

	PersonalFile[] getFilesList() throws DAOException;

	void writeFileData(PersonalFileFull pFile) throws DAOException;

	PersonalFile readFileData(PersonalFile pFile) throws DAOException;

	PersonalFileFull readFileData(PersonalFileFull pFile) throws DAOException;
}
