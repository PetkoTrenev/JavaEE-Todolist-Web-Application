package webapp.models;

public class Todo
{

	private int id;

	private User user;
	private String name;
	private String category;
	private Priority priority;

	public Todo(int id, User user, String name, String category, Priority priority) {
		this.id = id;
		this.user = user;
		this.name = name;
		this.category = category;
		this.priority = priority;
	}

	public Todo() {
	}

	public Todo(int id, String name, String category, Priority priority) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.priority = priority;
	}

	public Todo(User user, String name, String category, Priority priority) {
		this.user = user;
		this.name = name;
		this.category = category;
		this.priority = priority;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public void setCategory(String category)
	{
		this.category = category;
	}

	public void setPriority(Priority priority)
	{
		this.priority = priority;
	}

	public String getName()
	{
		return name;
	}

	public String getCategory()
	{
		return category;
	}

	public Priority getPriority()
	{
		return priority;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Todo other = (Todo) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "Todo [user=" + user + ", id=" + id + ", name=" + name + ", category=" + category + ", priority="
				+ priority + "]";
	}

}
