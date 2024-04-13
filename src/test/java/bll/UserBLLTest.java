package bll;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserBLLTest 
{
	private UserBLL userBll;
	
	//===============================
	
	@BeforeEach
	void initUserBLL()
	{
		try 
		{
			this.userBll = new UserBLL();
		} 
		catch (BLLException e) 
		{
			e.printStackTrace();
		}
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
	void selectByEmailAndPasswordWithWrongEmailAndWrongPasswordTrowBLLException()
	{
		String wrongEmail = "email@wrong.com";
		String wrongPassword = "wrong";
		
		assertThrows(BLLException.class, ()-> this.userBll.selectByEmailAndPassword(wrongEmail, wrongPassword), "selectByEmailAndPassword with wrong email and  wrong password should throw BLLException");
		
	}

}
