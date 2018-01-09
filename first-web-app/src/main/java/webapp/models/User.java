package webapp.models;

import java.util.ArrayList;
import java.util.List;

public class User
{

	private Long id;
	private String name;
	private String email;
	private String password;
	private List<Todo> todos;

	public User() {
	}

	public User(Long id, String name, String email, String password, List<Todo> todos) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		todos = new ArrayList<Todo>();
	}

	public List<Todo> getTodos()
	{
		return todos;
	}

	public void setTodos(List<Todo> todos)
	{
		this.todos = todos;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	@Override
	public String toString()
	{
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", todos=" + todos
				+ "]";
	}

}
