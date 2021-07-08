package by.javatraining.archive_server.entity;

import java.util.Date;

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

public class PersonalFileFull extends PersonalFile {

	private static final long serialVersionUID = 8123211540067157487L;

	private Date dateOfBirth;
	private Date dateOfEmployment;
	private Date dateOfDismissal;
	private int salary;

	public PersonalFileFull(PersonalFile copyFile) {
		super(copyFile.fileNumber, copyFile.surName, copyFile.firstName, copyFile.patronymic);
	}

	public PersonalFileFull(long fileNumber, String surName, String firstName, String patronymic, Date dateOfBirth,
			Date dateOfEmployment, Date dateOfDismissal, int salary) {
		super(fileNumber, surName, firstName, patronymic);

		this.dateOfBirth = dateOfBirth;
		this.dateOfEmployment = dateOfEmployment;
		this.dateOfDismissal = dateOfDismissal;
		this.salary = salary;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public Date getDateOfEmployment() {
		return dateOfEmployment;
	}

	public Date getDateOfDismissal() {
		return dateOfDismissal;
	}

	public int getSalary() {
		return salary;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setDateOfEmployment(Date dateOfEmployment) {
		this.dateOfEmployment = dateOfEmployment;
	}

	public void setDateOfDismissal(Date dateOfDismissal) {
		this.dateOfDismissal = dateOfDismissal;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((dateOfDismissal == null) ? 0 : dateOfDismissal.hashCode());
		result = prime * result + ((dateOfEmployment == null) ? 0 : dateOfEmployment.hashCode());
		result = prime * result + salary;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		PersonalFileFull other = (PersonalFileFull) obj;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null) {
				return false;
			}
		} else if (!dateOfBirth.equals(other.dateOfBirth)) {
			return false;
		}
		if (dateOfDismissal == null) {
			if (other.dateOfDismissal != null) {
				return false;
			}
		} else if (!dateOfDismissal.equals(other.dateOfDismissal)) {
			return false;
		}
		if (dateOfEmployment == null) {
			if (other.dateOfEmployment != null) {
				return false;
			}
		} else if (!dateOfEmployment.equals(other.dateOfEmployment)) {
			return false;
		}
		if (salary != other.salary) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "PersonalFileFull [dateOfBirth=" + dateOfBirth + ", dateOfEmployment=" + dateOfEmployment
				+ ", dateOfDismissal=" + dateOfDismissal + ", salary=" + salary + "]";
	}
}
