package bo;

public class Message {
	private int id;
	private String object;
	private String content;
	
	private int idUser;

	public Message() {
	}

	public Message(String object, String content, int idUser) 
	{
		this.object = object;
		this.content = content;
		this.idUser = idUser;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	
}
