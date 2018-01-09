package webapp.models;

public class Todo
{
	
	private int id;
	private Long userId;
	private String name;
	private String category;
	private Priority priority;

	public Todo(int id, Long userId, String name, String category, Priority priority) {
		this.userId = userId;
		this.id = id;
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

	public Long getUserId()
	{
		return userId;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
	}

	public String getName()
	{
		return name;
	}

	public String getCategory()
	{
		return category;
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

	public Priority getPriority()
	{
		return priority;
	}

	@Override
	public String toString()
	{
		return "Todo [userId=" + userId + ", id=" + id + ", name=" + name + ", category=" + category + ", priority="
				+ priority + "]";
	}
	
	

}
