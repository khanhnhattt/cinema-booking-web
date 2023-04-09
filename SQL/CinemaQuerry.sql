create database Cinema

create table Room
(
	RoomID int Primary Key,
	RoomName varchar(5),
	NoOfSeats int,
)

create table Seat
(
	SeatID int PRIMARY KEY IDENTITY(1,1),
	RowNo varchar(1),
	SeatNo int,
	RoomID int FOREIGN KEY references Room(RoomID),
)

create table Movie
(
	MovieID int PRIMARY KEY,
	Title varchar(256),
	Director varchar(256),
	[Cast] varchar(256),
	ReleaseDate date,
	Duration int,
	Rated varchar(32),
	MovieDesc varchar(1024),
	Poster varchar(256),
)

alter table movie 
add [View] int default(0)

alter table Movie 
add Background varchar(256)

create table Show
(
	ShowID int PRIMARY KEY IDENTITY(1,1),
	StartTime datetime,
	MovieID int FOREIGN KEY references Movie(MovieID),
	RoomID int FOREIGN KEY references Room(RoomID),
)

create table Booking
(
	BookingID int PRIMARY KEY IDENTITY(1,1),
	CustomerName nvarchar(32),
	CustomerTel varchar(32),
	TotalPrice int,
	ShowID int FOREIGN KEY references Show(ShowID),
)

create table SeatReserved
(
	SReservedID int FOREIGN KEY references Seat(SeatID),
	BookingID int FOREIGN KEY references Booking(BookingID),
	ShowID int FOREIGN KEY references Show(ShowID),
	PRIMARY KEY (SReservedID, ShowID)
)

create table Account 
(
	UserID int PRIMARY KEY IDENTITY(1, 1),
	Name varchar(256),
	[User] varchar(256) UNIQUE,
	Pass varchar(256)
)

INSERT INTO Movie VALUES 
	(1, 'Ant-Man and The Wasp: Quantumania', 'Peyton Reed', 'Kathryn Newton, Michael Douglas, Evangeline Lilly,...', '17 February 2023', 117, 'PG-13', 'Superhero partners Scott Lang (Paul Rudd) and Hope Van Dyne (Evangeline Lilly) return to continue their adventures as Ant-Man and the Wasp. Together, with Hope’s parents Hank Pym (Michael Douglas) and Janet Van Dyne (Michelle Pfeiffer), and Scott’s daughter Cassie Lang (Kathryn Newton), the family finds themselves exploring the Quantum Realm, interacting with strange new creatures and embarking on an adventure that will push them beyond the limits of what they thought possible. ANT-MAN AND THE WASP: QUANTUMANIA contains several sequences with flashing lights that may affect those who are susceptible to photosensitive epilepsy or have other photosensitivities.','poster/antman.png','poster/antman-bg.png'),
	(2, 'Puss in Boots: The Last Wish', 'Joel Crawford, Januel Mercado', 'DaVine Joy Randolph, Wagner Moura, Samson Kayo,...', '3 February 2023', 90, 'PG', 'This year, everyone’s favourite leche-loving, swashbuckling, fear-defying feline returns. For the first time in more than a decade, DreamWorks Animation presents a new adventure in the Shrek universe as daring outlaw Puss in Boots discovers that his passion for peril and disregard for safety have taken their toll. Puss has burned through eight of his nine lives, though he lost count along the way. Getting those lives back will send Puss in Boots on his grandest quest yet. But with only one life left, Puss will have to humble himself and ask for help from his former partner and nemesis: the captivating Kitty Soft Paws (Oscar® nominee Salma Hayek). Together, our trio of heroes will have to stay one step ahead of Goldilocks (Oscar® nominee Florence Pugh, Black Widow) and the Three Bears Crime Family, “Big” Jack Horner (Emmy winner John Mulaney, Big Mouth) and terrifying bounty hunter, The Big Bad Wolf (Wagner Moura, Narcos).','poster/pib.png','poster/pib-bg.png'),
	(3, 'Avatar: The Way of Water', 'James Cameron', 'Kate Winslet, Giovanni Ribisi, Stephen Lang,...', '16 December 2022', 162, 'PG-13', 'Experience the once-in-a-generation movie event, exclusively in cinemas. Set more than a decade after the events of the first film, AVATAR 2 begins to tell the story of the Sully family (Jake, Neytiri, and their kids), the trouble that follows them, the lengths they go to keep each other safe, the battles they fight to stay alive and the tragedies they endure. The latest in the groundbreaking fantasy saga from director James Cameron.','poster/avatar.png','poster/avatar-bg.png'),
	(4, 'Creed III', 'Michael B. Jordan', 'Florian Munteanu, Tessa Thompson, Wood Harris, Michael B. Jordan, Phylicia Rashad', '3 March 2023', 116, 'PG-13', 'After dominating the boxing world, Adonis Creed (Michael B. Jordan) has been thriving in both his career and family life. When a childhood friend and former boxing prodigy, Damian (Jonathan Majors), resurfaces after serving a long sentence in prison, he is eager to prove that he deserves his shot in the ring. The face-off between former friends is more than just a fight. To settle the score, Adonis must put his future on the line to battle Damian – a fighter who has nothing to lose. Creed III is the third installment in the successful franchise and is Michael B. Jordan’s directorial debut.','poster/creed.png','poster/creed-bg.png'),
	(5, 'Cocaine Bear', 'Elizabeth Banks', 'Kristofer Hivju, Brooklynn Prince, Jesse Tyler Ferguson,...', '24 February 2023', 95, 'R', 'Inspired by the 1985 true story of a drug runners plane crash, missing cocaine, and the black bear that ate it, this wild dark comedy finds an oddball group of cops, criminals, tourists, and teens converging in a Georgia forest where a 500- pound apex predator has ingested a staggering amount of cocaine and gone on a coke-fueled rampage for more blow … and blood.','poster/bear.png','poster/bear-bg.png');


INSERT INTO Room (RoomID, RoomName, NoOfSeats) VALUES (1, 1, 50), (2, 2, 30), (3, 3, 30), (4, 4, 30), (5, 5, 30);

INSERT INTO Seat (RowNo, SeatNo, RoomID) VALUES ('A', 1, 1), ('A', 2, 1), ('A', 3, 1), ('A', 4, 1), ('A', 5, 1), ('A', 6, 1), ('A', 7, 1), ('A', 8, 1), ('A', 9, 1), ('A', 10, 1), ('B', 1, 1), ('B', 2, 1), ('B', 3, 1), ('B', 4, 1), ('B', 5, 1), ('B', 6, 1), ('B', 7, 1), ('B', 8, 1), ('B', 9, 1), ('B', 10, 1), ('C', 1, 1), ('C', 2, 1), ('C', 3, 1), ('C', 4, 1), ('C', 5, 1), ('C', 6, 1), ('C', 7, 1), ('C', 8, 1), ('C', 9, 1), ('C', 10, 1), 
												('D', 1, 1), ('D', 2, 1), ('D', 3, 1), ('D', 4, 1), ('D', 5, 1), ('D', 6, 1), ('D', 7, 1), ('D', 8, 1), ('D', 9, 1), ('D', 10, 1), ('E', 1, 1), ('E', 2, 1), ('E', 3, 1), ('E', 4, 1), ('E', 5, 1), ('E', 6, 1), ('E', 7, 1), ('E', 8, 1), ('E', 9, 1), ('E', 10, 1),
												('A', 1, 2), ('A', 2, 2), ('A', 3, 2), ('A', 4, 2), ('A', 5, 2), ('A', 6, 2), ('A', 7, 2), ('A', 8, 2), ('A', 9, 2), ('A', 10, 2), ('B', 1, 2), ('B', 2, 2), ('B', 3, 2), ('B', 4, 2), ('B', 5, 2), ('B', 6, 2), ('B', 7, 2), ('B', 8, 2), ('B', 9, 2), ('B', 10, 2), ('C', 1, 2), ('C', 2, 2), ('C', 3, 2), ('C', 4, 2), ('C', 5, 2), ('C', 6, 2), ('C', 7, 2), ('C', 8, 2), ('C', 9, 2), ('C', 10, 2),
												('A', 1, 3), ('A', 2, 3), ('A', 3, 3), ('A', 4, 3), ('A', 5, 3), ('A', 6, 3), ('A', 7, 3), ('A', 8, 3), ('A', 9, 3), ('A', 10, 3), ('B', 1, 3), ('B', 2, 3), ('B', 3, 3), ('B', 4, 3), ('B', 5, 3), ('B', 6, 3), ('B', 7, 3), ('B', 8, 3), ('B', 9, 3), ('B', 10, 3), ('C', 1, 3), ('C', 2, 3), ('C', 3, 3), ('C', 4, 3), ('C', 5, 3), ('C', 6, 3), ('C', 7, 3), ('C', 8, 3), ('C', 9, 3), ('C', 10, 3),
												('A', 1, 4), ('A', 2, 4), ('A', 3, 4), ('A', 4, 4), ('A', 5, 4), ('A', 6, 4), ('A', 7, 4), ('A', 8, 4), ('A', 9, 4), ('A', 10, 4), ('B', 1, 4), ('B', 2, 4), ('B', 3, 4), ('B', 4, 4), ('B', 5, 4), ('B', 6, 4), ('B', 7, 4), ('B', 8, 4), ('B', 9, 4), ('B', 10, 4), ('C', 1, 4), ('C', 2, 4), ('C', 3, 4), ('C', 4, 4), ('C', 5, 4), ('C', 6, 4), ('C', 7, 4), ('C', 8, 4), ('C', 9, 4), ('C', 10, 4), 
												('A', 1, 5), ('A', 2, 5), ('A', 3, 5), ('A', 4, 5), ('A', 5, 5), ('A', 6, 5), ('A', 7, 5), ('A', 8, 5), ('A', 9, 5), ('A', 10, 5), ('B', 1, 5), ('B', 2, 5), ('B', 3, 5), ('B', 4, 5), ('B', 5, 5), ('B', 6, 5), ('B', 7, 5), ('B', 8, 5), ('B', 9, 5), ('B', 10, 5), ('C', 1, 5), ('C', 2, 5), ('C', 3, 5), ('C', 4, 5), ('C', 5, 5), ('C', 6, 5), ('C', 7, 5), ('C', 8, 5), ('C', 9, 5), ('C', 10, 5);

INSERT INTO Show (MovieID, RoomID, StartTime) VALUES(1, 3, dateadd(hour,1,getdate())), (1, 2, dateadd(hour,4,getdate())), (1, 1, dateadd(hour,7,getdate())), (1, 5, dateadd(hour,10,getdate())), (1, 4, dateadd(hour,13,getdate())), 
													(1, 3, dateadd(hour,25,getdate())), (1, 2, dateadd(hour,28,getdate())), (1, 1, dateadd(hour,31,getdate())), (1, 5, dateadd(hour,34,getdate())), (1, 4, dateadd(hour,37,getdate())), 
													(1, 3, dateadd(hour,49,getdate())), (1, 2, dateadd(hour,52,getdate())), (1, 1, dateadd(hour,55,getdate())), (1, 5, dateadd(hour,58,getdate())), (1, 4, dateadd(hour,61,getdate())), 
													(1, 3, dateadd(hour,73,getdate())), (1, 2, dateadd(hour,76,getdate())), (1, 1, dateadd(hour,79,getdate())), (1, 5, dateadd(hour,82,getdate())), (1, 4, dateadd(hour,85,getdate())), 
													(1, 3, dateadd(hour,97,getdate())), (1, 2, dateadd(hour,100,getdate())), (1, 1, dateadd(hour,103,getdate())), (1, 5, dateadd(hour,106,getdate())), (1, 4, dateadd(hour,109,getdate())),
													(2, 4, dateadd(hour,1,getdate())), (2, 3, dateadd(hour,4,getdate())), (2, 2, dateadd(hour,7,getdate())), (2, 1, dateadd(hour,10,getdate())), (2, 5, dateadd(hour,13,getdate())), 
													(2, 4, dateadd(hour,25,getdate())), (2, 3, dateadd(hour,28,getdate())), (2, 2, dateadd(hour,31,getdate())), (2, 1, dateadd(hour,34,getdate())), (2, 5, dateadd(hour,37,getdate())), 
													(2, 4, dateadd(hour,49,getdate())), (2, 3, dateadd(hour,52,getdate())), (2, 2, dateadd(hour,55,getdate())), (2, 1, dateadd(hour,58,getdate())), (2, 5, dateadd(hour,61,getdate())), 
													(2, 4, dateadd(hour,73,getdate())), (2, 3, dateadd(hour,76,getdate())), (2, 2, dateadd(hour,79,getdate())), (2, 1, dateadd(hour,82,getdate())), (2, 5, dateadd(hour,85,getdate())), 
													(2, 4, dateadd(hour,97,getdate())), (2, 3, dateadd(hour,100,getdate())), (2, 2, dateadd(hour,103,getdate())), (2, 1, dateadd(hour,106,getdate())), (2, 5, dateadd(hour,109,getdate())),
													(3, 5, dateadd(hour,1,getdate())), (3, 4, dateadd(hour,4,getdate())), (3, 3, dateadd(hour,7,getdate())), (3, 2, dateadd(hour,10,getdate())), (3, 1, dateadd(hour,13,getdate())), 
													(3, 5, dateadd(hour,25,getdate())), (3, 4, dateadd(hour,28,getdate())), (3, 3, dateadd(hour,31,getdate())), (3, 2, dateadd(hour,34,getdate())), (3, 1, dateadd(hour,37,getdate())), 
													(3, 5, dateadd(hour,49,getdate())), (3, 4, dateadd(hour,52,getdate())), (3, 3, dateadd(hour,55,getdate())), (3, 2, dateadd(hour,58,getdate())), (3, 1, dateadd(hour,61,getdate())), 
													(3, 5, dateadd(hour,73,getdate())), (3, 4, dateadd(hour,76,getdate())), (3, 3, dateadd(hour,79,getdate())), (3, 2, dateadd(hour,82,getdate())), (3, 1, dateadd(hour,85,getdate())), 
													(3, 5, dateadd(hour,97,getdate())), (3, 4, dateadd(hour,100,getdate())), (3, 3, dateadd(hour,103,getdate())), (3, 2, dateadd(hour,106,getdate())), (3, 1, dateadd(hour,109,getdate())), 
													(4, 1, dateadd(hour,25,getdate())), (4, 5, dateadd(hour,28,getdate())), (4, 4, dateadd(hour,31,getdate())), (4, 3, dateadd(hour,34,getdate())), (4, 2, dateadd(hour,37,getdate())), 
													(4, 1, dateadd(hour,49,getdate())), (4, 5, dateadd(hour,52,getdate())), (4, 4, dateadd(hour,55,getdate())), (4, 3, dateadd(hour,58,getdate())), (4, 2, dateadd(hour,61,getdate())), 
													(4, 1, dateadd(hour,73,getdate())), (4, 5, dateadd(hour,76,getdate())), (4, 4, dateadd(hour,79,getdate())), (4, 3, dateadd(hour,82,getdate())), (4, 2, dateadd(hour,85,getdate())), 
													(4, 1, dateadd(hour,97,getdate())), (4, 5, dateadd(hour,100,getdate())), (4, 4, dateadd(hour,103,getdate())), (4, 3, dateadd(hour,106,getdate())), (4, 2, dateadd(hour,109,getdate())),
													(5, 2, dateadd(hour,25,getdate())), (5, 1, dateadd(hour,28,getdate())), (5, 5, dateadd(hour,31,getdate())), (5, 4, dateadd(hour,34,getdate())), (5, 3, dateadd(hour,37,getdate())), 
													(5, 2, dateadd(hour,49,getdate())), (5, 1, dateadd(hour,52,getdate())), (5, 5, dateadd(hour,55,getdate())), (5, 4, dateadd(hour,58,getdate())), (5, 3, dateadd(hour,61,getdate())), 
													(5, 2, dateadd(hour,73,getdate())), (5, 1, dateadd(hour,76,getdate())), (5, 5, dateadd(hour,79,getdate())), (5, 4, dateadd(hour,82,getdate())), (5, 3, dateadd(hour,85,getdate())), 
													(5, 2, dateadd(hour,97,getdate())), (5, 1, dateadd(hour,100,getdate())), (5, 5, dateadd(hour,103,getdate())), (5, 4, dateadd(hour,106,getdate())), (5, 3, dateadd(hour,109,getdate()));

insert into Account values ('Khanh', 'khanh', '123'),
						   ('User', 'user', '123'),
						   ('Trang', 'trang', '321'),
						   ('HAHA', 'haha', '111'),
						   ('HEHE', 'hehe', '999');