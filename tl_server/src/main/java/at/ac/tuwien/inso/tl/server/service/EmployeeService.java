package at.ac.tuwien.inso.tl.server.service;

public interface EmployeeService
{
	/**
	 * Updates the lastLogInDate of the user with the given id
	 * @param id
	 */
	public void updateLoginDate(Integer id);
}
