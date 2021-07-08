package by.javatraining.archive_server.entity;

import java.io.Serializable;

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

public class PersonalFile implements Serializable {

	private static final long serialVersionUID = 2977812172030575645L;

	protected long fileNumber;
	protected String surName;
	protected String firstName;
	protected String patronymic;

	public PersonalFile(long fileNumber) {
		this.fileNumber = fileNumber;
	}

	public PersonalFile(long fileNumber, String surName, String firstName, String patronymic) {
		this(fileNumber);

		this.surName = surName;
		this.firstName = firstName;
		this.patronymic = patronymic;
	}

	public long getFileNumber() {
		return fileNumber;
	}

	public String getSurName() {
		return surName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setFileNumber(long fileNumber) {
		this.fileNumber = fileNumber;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (fileNumber ^ (fileNumber >>> 32));
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((patronymic == null) ? 0 : patronymic.hashCode());
		result = prime * result + ((surName == null) ? 0 : surName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		PersonalFile other = (PersonalFile) obj;
		if (fileNumber != other.fileNumber) {
			return false;
		}
		if (firstName == null) {
			if (other.firstName != null) {
				return false;
			}
		} else if (!firstName.equals(other.firstName)) {
			return false;
		}
		if (patronymic == null) {
			if (other.patronymic != null) {
				return false;
			}
		} else if (!patronymic.equals(other.patronymic)) {
			return false;
		}
		if (surName == null) {
			if (other.surName != null) {
				return false;
			}
		} else if (!surName.equals(other.surName)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "PersonalFile [fileNumber=" + fileNumber + ", surName=" + surName + ", firstName=" + firstName
				+ ", patronymic=" + patronymic + "]";
	}
}
