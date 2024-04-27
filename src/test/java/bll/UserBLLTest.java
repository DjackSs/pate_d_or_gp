package bll;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import bo.Message;
import bo.Reservation;
import bo.Schedule;
import bo.User;
import dal.DALException;
import dal.DAOFactory;
import dal.UserDAO;


@ExtendWith(MockitoExtension.class)
@DisplayName("Test for UserBLL")
class UserBLLTest 
{

	@BeforeAll
	static void initFactory()
	{
		mockStatic(DAOFactory.class);
	}
	
	//===============================
	//selectByEmailAndPassword
	
	@Nested
	@DisplayName("Testing user login ")
	class selectByEmailAndePassword
	{
		@InjectMocks
		private UserBLL userBll;
		
		@Mock
		private UserDAO dao;
		
		
		//===============================
		
		
		@BeforeEach
		void initUserBLL() throws DALException, BLLException
		{
			when(DAOFactory.getUserDAO()).thenReturn(this.dao);
			
			this.userBll = new UserBLL();
		}

		@AfterEach
		void destroyUserBLL()
		{
			this.userBll = null;
		}
		
		//===============================
		
		@Test
		void selectByEmailAndPassword_withValidEmailAndPassword_returnUser() throws DALException, BLLException
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
		void selectByEmailAndPassword_withEmailNullAndPasswordNull_throwBLLException()
		{
			assertThrows(BLLException.class, ()-> this.userBll.selectByEmailAndPassword(null, null), "selectByEmailAndPassword with email = null and password = null should throw BLLException");
			
		}
		
		//-----------------------------------
		
		@Test
		void selectByEmailAndPassword_withEmailEmptyAndPasswordEmpty_throwBLLException()
		{
			String emptyEmail = "                                 ";
			String emptyPassword = "";
			
			assertThrows(BLLException.class, ()-> this.userBll.selectByEmailAndPassword(emptyEmail, emptyPassword), "selectByEmailAndPassword with empty email and empty password should throw BLLException");
			
		}
		
		//-----------------------------------
		
		@Test
		void selectByEmailAndPassword_withWrongEmailAndWrongPassword_trowBLLException() throws DALException
		{
			String wrongEmail = "email@wrong.com";
			String wrongPassword = "wrong";
			
			when(this.dao.selectByEmailAndPassword(Mockito.anyString(), Mockito.anyString())).thenThrow(DALException.class);
			
			assertThrows(BLLException.class, ()-> this.userBll.selectByEmailAndPassword(wrongEmail, wrongPassword), "selectByEmailAndPassword with wrong email and wrong password should throw BLLException");
			
		}
		
	}
	
		
	
	//===============================
	//insert
	
	@Nested
	@DisplayName("Testing creating user")
	class insert
	{
		@InjectMocks
		private UserBLL userBll;
		
		@Mock
		private UserDAO dao;
		
		//===============================
		
		
		@BeforeEach
		void initUserBLL() throws DALException, BLLException
		{
			when(DAOFactory.getUserDAO()).thenReturn(this.dao);
			
			this.userBll = new UserBLL();
		}

		@AfterEach
		void destroyUserBLL()
		{
			this.userBll = null;
		}
		
		//===============================
		
		@Test
		void insert_validUser_returnUser() throws DALException, BLLException
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
		void insert_alreadyExistingUser_throwBLLException() throws DALException, BLLException
		{
			User existingUser = new User();
			existingUser.setName("existingUserName");
			existingUser.setLastname("existingUserLastName");	
			existingUser.setEmail("existingUser@mail.com");
			existingUser.setPassword("ValidPassord1!");
			
			when(this.dao.selectByEmailAndPassword(Mockito.anyString(), Mockito.anyString() )).thenReturn(existingUser);
			
			assertThrows(BLLException.class, ()-> this.userBll.insert(existingUser), "insert with a User already in database should throw BLLException");
			
		}
		
		//-----------------------------------
		
		@Test
		void insert_emptyUser_throwBLLException()
		{
			User emptyUser = new User();
			
			assertThrows(BLLException.class, ()-> this.userBll.insert(emptyUser), "insert with empty User should throw BLLException");
			
		}
		
		//-----------------------------------
		
		@Test
		void insert_withEmailThatDoNotMatchRegex_throwBLLException()
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
		void insert_withPasswordThatDoNotMatchRegex_throwBLLException()
		{
			User invalidUser = new User();
			invalidUser.setName("userName");
			invalidUser.setLastname("userLastName");	
			invalidUser.setEmail("user@mail.com");
			invalidUser.setPassword("invalidPassword");
			
			assertThrows(BLLException.class, ()-> this.userBll.insert(invalidUser), "insert with invalid User password should throw BLLException");
			
		}

	}
	
	//===============================
	//insertMessage
	
	@Nested
	@DisplayName("Testing creating message")
	class insertMessage
	{
		@InjectMocks
		private UserBLL userBll;
		
		@Mock
		private UserDAO dao;
		
		//===============================
		
		
		
		@BeforeEach
		void initUserBLL() throws DALException, BLLException
		{
			when(DAOFactory.getUserDAO()).thenReturn(this.dao);
			
			this.userBll = new UserBLL();
		}

		@AfterEach
		void destroyUserBLL()
		{
			this.userBll = null;
		}
		
		//===============================
		
		@Test
		void insertMessage_validMessage_returnMessage() throws DALException, BLLException
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
		void insertMessage_withEmptyMessage_throwBLLException()
		{
			Message EmptyMessage = new Message();
			
			assertThrows(BLLException.class, ()-> this.userBll.insertMessage(EmptyMessage), "insert with empty Message should throw BLLException");
		}
		
			
	}
	
	//===============================
	//insertReservation
	
	@Nested
	@DisplayName("Testing creating reservation")
	class insertReservation
	{
		@InjectMocks
		private UserBLL userBll;
		
		@Mock
		private UserDAO dao;
		
		private static List<Schedule> Schedules;
		private static DateTimeFormatter dateFormatter;
		
		//===============================
		
		
		@BeforeAll
		static void initSchedules()
		{
			Schedules = new ArrayList<>();
			Schedules.add(new Schedule(LocalTime.of(8, 00), LocalTime.of(12, 00)));
			Schedules.add(new Schedule(LocalTime.of(14, 00), LocalTime.of(16, 00)));
			Schedules.add(new Schedule(LocalTime.of(18, 00), LocalTime.of(23, 00)));
		}
		
		@BeforeAll
		static void initFormatter()
		{
			dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		}
		
		@BeforeEach
		void initUserBLL() throws DALException, BLLException
		{
			when(DAOFactory.getUserDAO()).thenReturn(this.dao);
			
			this.userBll = new UserBLL();
		}

		@AfterEach
		void destroyUserBLL()
		{
			this.userBll = null;
		}
		
		//===============================
		
		@Test
		void insertReservation_withValidDateAndValidTime_returnReservation() throws DALException, BLLException
		{
			String validDate = LocalDateTime.now().plusDays(1).format(dateFormatter);
			String validTime = "15:00";
			
			Mockito.doNothing().when(this.dao).insertReservation(Mockito.any(Reservation.class));
			
			Reservation validReservation = null;
			
			validReservation = this.userBll.insertReservation(validDate, validTime, Schedules);
			
			assertNotNull(validReservation);
				
			
		}
		
		//-----------------------------------
		
		@Test
		void insertReservation_withEmptyDateAndEmptyTime_throwBLLException() throws DALException, BLLException
		{
			String emptyDate = "                                      ";
			String emptyTime = "";
			
			assertThrows(BLLException.class, ()-> this.userBll.insertReservation(emptyDate, emptyTime, Schedules), "insertReservation with empty date and empty time should throw BLLException");
				
		}
		
		//-----------------------------------
		
		@Test
		void insertReservation_withWrongDateFormat_throwBLLException() throws DALException, BLLException
		{
			DateTimeFormatter wrongdateFormatter = DateTimeFormatter.ofPattern("MM-yyy-dd");
			
			String wrongDateFormat = LocalDateTime.now().plusDays(1).format(wrongdateFormatter);                                   
			String validTime = "15:00";
			
			assertThrows(BLLException.class, ()-> this.userBll.insertReservation(wrongDateFormat, validTime, Schedules), "insertReservation with wrong date format should throw BLLException");	
			
		}
		
		//-----------------------------------
		
		@Test
		void insertReservation_withPassedDate_throwBLLException() throws DALException, BLLException
		{
			String passedfDate = LocalDateTime.now().plusDays(-1).format(dateFormatter);                                   
			String validTime = "15:00";
			
			assertThrows(BLLException.class, ()-> this.userBll.insertReservation(passedfDate, validTime, Schedules), "insertReservation with passed date should throw BLLException");

		}
		
		//-----------------------------------
		
		@Test
		void insertReservation_withWrongTimeFormat_throwBLLException() throws DALException, BLLException
		{
			String validDate = LocalDateTime.now().plusDays(1).format(dateFormatter);                                   
			String wrongTimeFormat = "15h00";
			
			assertThrows(BLLException.class, ()-> this.userBll.insertReservation(validDate, wrongTimeFormat, Schedules), "insertReservation with wrong date format should throw BLLException");
			
		}
		
		//-----------------------------------
		
		@Test
		void insertReservation_withTimeOutsideSchedules_throwBLLException() throws DALException, BLLException
		{
			String validDate = LocalDateTime.now().plusDays(1).format(dateFormatter);                                   
			String outsideSchedulesTime = "13:00";
			
			assertThrows(BLLException.class, ()-> this.userBll.insertReservation(validDate, outsideSchedulesTime, Schedules), "insertReservation with passed date should throw BLLException");
				
		}
		
	}
	
	//===============================
	//update
	
	@Nested
	@DisplayName("Testing update user")
	class update
	{
		
		@InjectMocks
		private UserBLL userBll;
		
		@Mock
		private UserDAO dao;
		
		private static User dataBaseUser;
		
		//===============================
		
		@BeforeAll
		static void initDataUser()
		{
			dataBaseUser = new User("userName", "userLastname", "userEmail@mail.com", "", "cust");
		}
		
		
		@BeforeEach
		void initUserBLL() throws DALException, BLLException
		{
			when(DAOFactory.getUserDAO()).thenReturn(this.dao);
			
			this.userBll = new UserBLL();
		}

		@AfterEach
		void destroyUserBLL()
		{
			this.userBll = null;
		}
		
		//===============================
		
		@Test
		void update_withSameUser_returnDataBaseUser() throws DALException, BLLException
		{
			User sameUser = dataBaseUser;
			
			when(this.dao.selectById(sameUser.getId())).thenReturn(dataBaseUser);
			
			Mockito.doNothing().when(this.dao).update(sameUser);
			
			User updateUser = this.userBll.update(sameUser);
			
			assertAll("updateUser should be same as databaseUser",
				    () -> assertEquals(updateUser.getName(), dataBaseUser.getName()),
				    () -> assertEquals(updateUser.getLastname(), dataBaseUser.getLastname()),
				    () -> assertEquals(updateUser.getEmail(), dataBaseUser.getEmail()),
				    () -> assertEquals(updateUser.getPassword(), dataBaseUser.getPassword()),
				    () -> assertEquals(updateUser.getRole(), dataBaseUser.getRole())
				    
				);
	
		}
		
		//-----------------------------------
		
		@Test
		void update_withEmptyUser_returnDataBaseUser() throws DALException, BLLException
		{
			User emptyUser = new User();
			
			when(this.dao.selectById(emptyUser.getId())).thenReturn(dataBaseUser);
			
			Mockito.doNothing().when(this.dao).update(emptyUser);
			
			User updateUser = this.userBll.update(emptyUser);
			
			assertAll("updateUser should be same as databaseUser",
				    () -> assertEquals(updateUser.getName(), dataBaseUser.getName()),
				    () -> assertEquals(updateUser.getLastname(), dataBaseUser.getLastname()),
				    () -> assertEquals(updateUser.getEmail(), dataBaseUser.getEmail()),
				    () -> assertEquals(updateUser.getPassword(), dataBaseUser.getPassword()),
				    () -> assertEquals(updateUser.getRole(), dataBaseUser.getRole())
				    
				);
	
		}
		
		//-----------------------------------
		
		@Test
		void update_withDifferentValues_returnUpdatedUserWithNewValues() throws DALException, BLLException
		{
			User changedUser = new User("changedUserName", "changedUserLastname", "changedUserEamail@mail.com", "UserPassword1!", "cust");
			
			when(this.dao.selectById(changedUser.getId())).thenReturn(dataBaseUser);
			
			Mockito.doNothing().when(this.dao).update(changedUser);
			
			User updateUser = this.userBll.update(changedUser);
			
			assertAll("updateUser should not be same as changedUser",
				    () -> assertEquals(updateUser.getName(), changedUser.getName()),
				    () -> assertEquals(updateUser.getLastname(), changedUser.getLastname()),
				    () -> assertEquals(updateUser.getEmail(), changedUser.getEmail()),
				    //password is erased by the bll = ""
				    () -> assertEquals(updateUser.getPassword(), dataBaseUser.getPassword()),
				    () -> assertEquals(updateUser.getRole(), changedUser.getRole())
				    
				);
	
			
		}
		
		//-----------------------------------
		
		@Test
		void update_withDifferentEmailAndAPassword_returnUpdatedUserWithNewValues() throws DALException, BLLException
		{
			User changedUser = new User();
			changedUser.setEmail("changedUserEmail@mail.com");
			changedUser.setPassword("UserPassword1!");
			
			when(this.dao.selectById(changedUser.getId())).thenReturn(dataBaseUser);
			
			Mockito.doNothing().when(this.dao).update(changedUser);
			
			User updateUser = this.userBll.update(changedUser);
			
			assertAll("updateUser should not be same as changedUser",
				    () -> assertEquals(updateUser.getName(), dataBaseUser.getName()),
				    () -> assertEquals(updateUser.getLastname(), dataBaseUser.getLastname()),
				    () -> assertEquals(updateUser.getEmail(), changedUser.getEmail()),
				    //password is erased by the bll = ""
				    () -> assertEquals(updateUser.getPassword(), dataBaseUser.getPassword()),
				    () -> assertEquals(updateUser.getRole(), dataBaseUser.getRole())
				    
				);
			
		}
		
		//-----------------------------------
		
		@Test
		void update_withDifferentEmailWithoutPassword_throwBLLException() throws DALException, BLLException
		{
			User changedUser = new User();
			changedUser.setEmail("changedUserEmail@mail.com");
			
			when(this.dao.selectById(changedUser.getId())).thenReturn(dataBaseUser);
			
			assertThrows(BLLException.class, ()-> this.userBll.update(changedUser), "Change email without the password should throw BLLException");
			
		}
		
		//-----------------------------------
		
		@Test
		void update_withEmailThatDoNotMatchRegex_throwBLLException() throws DALException, BLLException
		{
			User changedUser = new User();
			changedUser.setEmail("changedEmail");
			
			when(this.dao.selectById(changedUser.getId())).thenReturn(dataBaseUser);
			
			assertThrows(BLLException.class, ()-> this.userBll.update(changedUser), "Change email with invalid email should throw BLLException");
			
		}
		
		//-----------------------------------
		
		@Test
		void update_withPassswordThatDoNotMatchRegex_throwBLLException() throws DALException, BLLException
		{
			User changedUser = new User();
			changedUser.setPassword("UserPassword");
			
			when(this.dao.selectById(changedUser.getId())).thenReturn(dataBaseUser);
			
			assertThrows(BLLException.class, ()-> this.userBll.update(changedUser), "Change password with invalid password should throw BLLException");
			
		}
		
		
		
	}
	
	
	
	
	
	
	
	
	

}
