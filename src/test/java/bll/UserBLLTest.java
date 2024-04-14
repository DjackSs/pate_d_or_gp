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
	void selectByEmailAndPasswordWithEmailNullAndPasswordNullThrowBLLException()
	{
		assertThrows(BLLException.class, ()-> this.userBll.selectByEmailAndPassword(null, null), "selectByEmailAndPassword with email = null and password = null should throw BLLException");
		
	}
	
	//-----------------------------------
	
	@Test
	void selectByEmailAndPasswordWithEmailEmptyAndPasswordEmptyThrowBLLException()
	{
		String emptyEmail = "                                 ";
		String emptyPassword = "";
		
		assertThrows(BLLException.class, ()-> this.userBll.selectByEmailAndPassword(emptyEmail, emptyPassword), "selectByEmailAndPassword with empty email and empty password should throw BLLException");
		
	}
	
	//-----------------------------------
	
	@Test
	void selectByEmailAndPasswordWithWrongEmailAndWrongPasswordTrowBLLException() throws DALException
	{
		String wrongEmail = "email@wrong.com";
		String wrongPassword = "wrong";
		
		when(this.dao.selectByEmailAndPassword(Mockito.anyString(), Mockito.anyString() )).thenThrow(DALException.class);
		
		assertThrows(BLLException.class, ()-> this.userBll.selectByEmailAndPassword(wrongEmail, wrongPassword), "selectByEmailAndPassword with wrong email and wrong password should throw BLLException");
		
	}
	
	//-----------------------------------
	
		@Test
		void selectByEmailAndPasswordWithValidEmailAndPassword() throws DALException
		{
			
			String Email = "email@right.com";
			String Password = "R1ght!";
			
			User userMock = new User();
			
			when(this.dao.selectByEmailAndPassword(Mockito.anyString(), Mockito.anyString() )).thenReturn(userMock);
			
			User userResult = null;
			
			try 
			{
				userResult = this.userBll.selectByEmailAndPassword(Email, Password);
			} 
			catch (BLLException e) 
			{
				for(String error : e.getErrors().values())
				{
					System.out.println(error);
			
				};
				
			}
			
			assertNotNull(userResult);
			
			
			
			
		}

}
