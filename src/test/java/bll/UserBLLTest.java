package bll;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import bo.Message;
import bo.User;
import dal.DALException;
import dal.UserDAO;


@ExtendWith(MockitoExtension.class)
class UserBLLTest 
{
	@InjectMocks
	private UserBLL userBll;
	
	@Mock
	private UserDAO dao;
	
	//===============================
	
	@BeforeEach
	void initUserBLL()
	{
		this.userBll = new UserBLL(this.dao);
	}

	@AfterEach
	void destroyUserBLL()
	{
		this.userBll = null;
	}
	
	//===============================
	//selectByEmailAndPassword
	
		
	@Test
	void selectByEmailAndPassword_WithValidEmailAndPassword_returnUser() throws DALException, BLLException
	{
		
		String Email = "email@right.com";
		String Password = "R1ght!";
		
		User userMock = new User();
		
		when(this.dao.selectByEmailAndPassword(Mockito.anyString(), Mockito.anyString() )).thenReturn(userMock);
		
		User userResult = null;
		
		userResult = this.userBll.selectByEmailAndPassword(Email, Password);
		
		assertNotNull(userResult);
		
			
	}
	
	//-----------------------------------

	@Test
	void selectByEmailAndPassword_WithEmailNullAndPasswordNull_ThrowBLLException()
	{
		assertThrows(BLLException.class, ()-> this.userBll.selectByEmailAndPassword(null, null), "selectByEmailAndPassword with email = null and password = null should throw BLLException");
		
	}
	
	//-----------------------------------
	
	@Test
	void selectByEmailAndPassword_WithEmailEmptyAndPasswordEmpty_ThrowBLLException()
	{
		String emptyEmail = "                                 ";
		String emptyPassword = "";
		
		assertThrows(BLLException.class, ()-> this.userBll.selectByEmailAndPassword(emptyEmail, emptyPassword), "selectByEmailAndPassword with empty email and empty password should throw BLLException");
		
	}
	
	//-----------------------------------
	
	@Test
	void selectByEmailAndPassword_WithWrongEmailAndWrongPassword_TrowBLLException() throws DALException
	{
		String wrongEmail = "email@wrong.com";
		String wrongPassword = "wrong";
		
		when(this.dao.selectByEmailAndPassword(Mockito.anyString(), Mockito.anyString())).thenThrow(DALException.class);
		
		assertThrows(BLLException.class, ()-> this.userBll.selectByEmailAndPassword(wrongEmail, wrongPassword), "selectByEmailAndPassword with wrong email and wrong password should throw BLLException");
		
	}
	
	
	//===============================
	//insert
	
	@Test
	void insert_ValidUser_returnUser() throws DALException, BLLException
	{
		User validUser = new User();
		validUser.setName("userName");
		validUser.setLastname("userLastName");	
		validUser.setEmail("user@mail.com");
		validUser.setPassword("ValidPassord1!");
		
		when(this.dao.selectByEmailAndPassword(Mockito.anyString(), Mockito.anyString() )).thenThrow(DALException.class);
		
		Mockito.doNothing().when(this.dao).insert(validUser);
		
		validUser = this.userBll.insert(validUser);
		
		assertNotNull(validUser);
		
	}
	
	//-----------------------------------
	
	@Test
	void insert_EmptyUser_TrowBLLException()
	{
		User emptyUser = new User();
		
		assertThrows(BLLException.class, ()-> this.userBll.insert(emptyUser), "insert with empty User should throw BLLException");
		
	}
	
	//-----------------------------------
	
	@Test
	void insert_WithEmailThatDoNotMatchRegex_TrowBLLException()
	{
		User invalidUser = new User();
		invalidUser.setName("userName");
		invalidUser.setLastname("userLastName");	
		invalidUser.setEmail("invalidEmail");
		invalidUser.setPassword("ValidPassord1!");
		
		assertThrows(BLLException.class, ()-> this.userBll.insert(invalidUser), "insert with invalid User email should throw BLLException");
		
	}
	
	//-----------------------------------
	
	@Test
	void insert_WithPasswordThatDoNotMatchRegex_TrowBLLException()
	{
		User invalidUser = new User();
		invalidUser.setName("userName");
		invalidUser.setLastname("userLastName");	
		invalidUser.setEmail("user@mail.com");
		invalidUser.setPassword("invalidPassword");
		
		assertThrows(BLLException.class, ()-> this.userBll.insert(invalidUser), "insert with invalid User password should throw BLLException");
		
	}
	
	//===============================
	//insertMessage
	
	
	@Test
	void insertMessage_ValidMessage_returnMessage() throws DALException, BLLException
	{
		Message validMessage = new Message();
		validMessage.setObject("messageOblect");
		validMessage.setContent("messageContent");
		
		Mockito.doNothing().when(this.dao).insertMessage(validMessage);
		
		validMessage = this.userBll.insertMessage(validMessage);
		
		assertNotNull(validMessage);
		
	}
	
	//-----------------------------------
	
	@Test
	void insertMessage_EmptyMessage_TrowBLLException()
	{
		Message EmptyMessage = new Message();
		
		assertThrows(BLLException.class, ()-> this.userBll.insertMessage(EmptyMessage), "insert with empty Message should throw BLLException");
		
	}
	
	
	
	
	
	

}
