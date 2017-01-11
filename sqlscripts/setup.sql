# Drop database sums if it exists already
DROP DATABASE IF EXISTS sums;

# Create database
CREATE DATABASE sums;

# Choose database
USE sums;

# UTF-8 encode
SET NAMES 'utf8';

create table user(
userId			int				NOT NULL AUTO_INCREMENT PRIMARY KEY,
userPassword	varchar(255)	NOT NULL,
userFirstName	varchar(30)		NOT NULL,
userLastName	varchar(30)		NOT NULL,
userGender		varchar(10)		NOT NULL,
userBirth		date			NOT NULL,
userEmail		varchar(30)		NOT NULL,
userPhone		varchar(30)
);

create table club(
clubId			int				NOT NULL AUTO_INCREMENT PRIMARY KEY,
clubName		varchar(30)		NOT NULL,
clubCategory	varchar(30)		NOT NULL,
clubInitDate	date		NOT NULL,
clubStatus		varchar(15) 	NOT NULL,
clubNote		varchar(500),
clubOwner int NOT NULL
);

create table role(
roleId			int				NOT NULL AUTO_INCREMENT PRIMARY KEY,
roleTitle		varchar(30)		NOT NULL,
rolePrivilege	varchar(30)		NOT NULL,
userId			int				NOT NULL,
clubId			int				NOT NULL,
roleInitDate	date		NOT NULL,
roleStatus		varchar(10)		NOT NULL
);

create table activity(
actId			int				NOT NULL AUTO_INCREMENT PRIMARY KEY,
clubId			int				NOT NULL,
actName			varchar(30)		NOT NULL,
actStartDate	datetime		NOT NULL,
actEndDate		datetime		NOT NULL,
actStatus		varchar(15) 	NOT NULL,
actCapacity		int				NOT NULL,
actLocation		varchar(300)	NOT NULL,
actBudget		int,
actAssignee		int 			NOT NULL,
actNote			varchar(500)
);

create table clubRegistrationRequest(
cRequestId		int				NOT NULL AUTO_INCREMENT PRIMARY KEY,
userId			int				NOT NULL,
clubId			int				NOT NULL,
requestCRole	varchar(30)		NOT NULL,
requestDate		datetime		NOT NULL,
requestStatus	varchar(10)
);

create table activityRegistrationRequest(
aRequestId		int				NOT NULL AUTO_INCREMENT PRIMARY KEY,
userId			int				NOT NULL,
actId			int				NOT NULL,
requestDate		datetime		NOT NULL,
requestStatus	varchar(10)		NOT NULL
);

create table clubNews(
newsId 			int 			NOT NULL AUTO_INCREMENT PRIMARY KEY,
newsTitle		varchar(500)	NOT NULL,
newsContent		varchar(3000)	NOT NULL,
newsCategory	varchar(100)	NOT NULL,
effectiveDate 	datetime		NOT NULL,
releaseDate		datetime		NOT NULL,
userId 			int 			NOT NULL,
clubId			int				NOT NULL
);

create table message(
mesId			int				NOT NULL PRIMARY KEY,
mesSender		int				NOT NULL,
mesReceiver		int				NOT NULL,
mesSendTime		datetime		NOT NULL,
mesSubject		varchar(40)		NOT NULL,
mesCC			varchar(40),
mesBCC			varchar(40),
mesDetails		varchar(2000)
);


# insert data
#-----------------------------------------------------------------------------------------
INSERT INTO user (userId, userPassword, userFirstName, userLastName, userGender, userBirth, userEmail, userPhone)
  VALUES (100001, 'password', 'Lee', 'C', 'Male',  CURDATE(), '710720299@gmail.com', '0294023940');

INSERT INTO user (userId, userPassword, userFirstName, userLastName, userGender, userBirth, userEmail, userPhone)
  VALUES (100002, 'password', 'Gray', 'Y', 'Female',  CURDATE(), 'xiaoxiao@gmail.com', '0294023940');

INSERT INTO user (userId, userPassword, userFirstName, userLastName, userGender, userBirth, userEmail, userPhone)
  VALUES (100003, 'password', 'Candy', 'H', 'Female',  CURDATE(), 'm.y.@gmail.com', '0294023940');

INSERT INTO user (userId, userPassword, userFirstName, userLastName, userGender, userBirth, userEmail, userPhone)
  VALUES (100004, 'password', 'Chany', 'Y', 'Female',  CURDATE(), 'chanyu@gmail.com', '0294023940');




INSERT INTO club (clubId, clubName, clubCategory, clubInitDate, clubStatus, clubNote, clubOwner)
  VALUES (1001, 'CSSA', 'Test', CURDATE(), 'ACTIVE', 'This is only for test', 100001);

INSERT INTO club (clubId, clubName, clubCategory, clubInitDate, clubStatus, clubNote, clubOwner)
  VALUES (1002, 'Music Night', 'Test', CURDATE(), 'ACTIVE', 'This is only for test', 100002);

INSERT INTO club (clubId, clubName, clubCategory, clubInitDate, clubStatus, clubNote, clubOwner)
  VALUES (1003, 'Good and Evil', 'Test', CURDATE(), 'ACTIVE', 'This is only for test', 100003);

INSERT INTO club (clubId, clubName, clubCategory, clubInitDate, clubStatus, clubNote, clubOwner)
  VALUES (1004, 'Foo', 'Test', CURDATE(), 'ACTIVE', 'None One Join this club yet', 100004);



INSERT INTO activity (actId, clubId, actName, actStartDate, actEndDate, actStatus, actCapacity, actLocation, actBudget, actAssignee, actNote)
	VALUES (10001, 1001, 'Trip to  USA', curtime(), curtime(), 'ACTIVE', 100, 'Oakville', 400, 100001, 'test');

INSERT INTO activity (actId, clubId, actName, actStartDate, actEndDate, actStatus, actCapacity, actLocation, actBudget, actAssignee, actNote)
	VALUES (10002, 1001, 'Halloween Treat', curtime(), curtime(), 'ACTIVE', 100, 'Oakville', 400, 100001, 'test');

INSERT INTO activity (actId, clubId, actName, actStartDate, actEndDate, actStatus, actCapacity, actLocation, actBudget, actAssignee, actNote)
	VALUES (10003, 1002, 'Coldplay Concert', curtime(), curtime(), 'ACTIVE', 100, 'Oakville', 400, 100001, 'test');

INSERT INTO activity (actId, clubId, actName, actStartDate, actEndDate, actStatus, actCapacity, actLocation, actBudget, actAssignee, actNote)
	VALUES (10004, 1002, 'TIFF', curtime(), curtime(), 'ACTIVE', 100, 'Oakville', 400, 100001, 'test');


INSERT INTO role (roleId, roleTitle, rolePrivilege, userId, clubId, roleInitDate, roleStatus)
	VALUES (1000000, 'Manager', 'ADMIN', 100001, 1001, CURDATE(), 'ACTIVE');

INSERT INTO role (roleId, roleTitle, rolePrivilege, userId, clubId, roleInitDate, roleStatus)
	VALUES (1000001, 'Member', 'GUEST', 100002, 1001, CURDATE(), 'ACTIVE');

INSERT INTO role (roleId, roleTitle, rolePrivilege, userId, clubId, roleInitDate, roleStatus)
	VALUES (1000002, 'Manager', 'ADMIN', 100002, 1002, CURDATE(), 'ACTIVE');

INSERT INTO role (roleId, roleTitle, rolePrivilege, userId, clubId, roleInitDate, roleStatus)
	VALUES (1000003, 'Member', 'GUEST', 100001, 1002, CURDATE(), 'ACTIVE');

INSERT INTO role (roleId, roleTitle, rolePrivilege, userId, clubId, roleInitDate, roleStatus)
	VALUES (1000004, 'Manager', 'ADMIN', 100003, 1003, CURDATE(), 'ACTIVE');

INSERT INTO role (roleId, roleTitle, rolePrivilege, userId, clubId, roleInitDate, roleStatus)
	VALUES (1000005, 'Member', 'GUEST', 100001, 1003, CURDATE(), 'ACTIVE');

INSERT INTO role (roleId, roleTitle, rolePrivilege, userId, clubId, roleInitDate, roleStatus)
	VALUES (1000006, 'Member', 'GUEST', 100002, 1003, CURDATE(), 'ACTIVE');

INSERT INTO role (roleId, roleTitle, rolePrivilege, userId, clubId, roleInitDate, roleStatus)
	VALUES (1000007, 'Manager', 'ADMIN', 100004, 1004, CURDATE(), 'ACTIVE');




INSERT INTO clubregistrationrequest (cRequestId, userId, clubId, requestCRole, requestDate, requestStatus)
	VALUES (100001, 100001, 1001, 'Manager', CURDATE(), 'ACTIVE');

INSERT INTO clubregistrationrequest (cRequestId, userId, clubId, requestCRole, requestDate, requestStatus)
	VALUES (100002, 100002, 1001, 'GUEST', CURDATE(), 'ACTIVE');

INSERT INTO clubregistrationrequest (cRequestId, userId, clubId, requestCRole, requestDate, requestStatus)
	VALUES (100003, 100002, 1002, 'Manager', CURDATE(), 'ACTIVE');

INSERT INTO clubregistrationrequest (cRequestId, userId, clubId, requestCRole, requestDate, requestStatus)
	VALUES (100004, 100001, 1002, 'GUEST', CURDATE(), 'ACTIVE');
    
INSERT INTO clubregistrationrequest (cRequestId, userId, clubId, requestCRole, requestDate, requestStatus)
	VALUES (100005, 100003, 1003, 'Manager', CURDATE(), 'ACTIVE');

INSERT INTO clubregistrationrequest (cRequestId, userId, clubId, requestCRole, requestDate, requestStatus)
	VALUES (100006, 100001, 1003, 'GUEST', CURDATE(), 'ACTIVE');

INSERT INTO clubregistrationrequest (cRequestId, userId, clubId, requestCRole, requestDate, requestStatus)
	VALUES (100007, 100002, 1003, 'GUEST', CURDATE(), 'ACTIVE');

INSERT INTO clubregistrationrequest (cRequestId, userId, clubId, requestCRole, requestDate, requestStatus)
	VALUES (100008, 100004, 1004, 'Manager', CURDATE(), 'ACTIVE');






INSERT INTO activityRegistrationRequest( aRequestId, userId, actId, requestDate, requestStatus)
	VALUES (100001, 100001, 10001, CURDATE(), 'Approved');

INSERT INTO activityRegistrationRequest( aRequestId, userId, actId, requestDate, requestStatus)
	VALUES (100002, 100001, 10002, CURDATE(), 'Approved');

INSERT INTO activityRegistrationRequest( aRequestId, userId, actId, requestDate, requestStatus)
	VALUES (100003, 100001, 10003, CURDATE(), 'Approved');

INSERT INTO activityRegistrationRequest( aRequestId, userId, actId, requestDate, requestStatus)
	VALUES (100004, 100001, 10004, CURDATE(), 'Approved');

INSERT INTO activityRegistrationRequest( aRequestId, userId, actId, requestDate, requestStatus)
	VALUES (100005, 100002, 10001, CURDATE(), 'Approved');

INSERT INTO activityRegistrationRequest( aRequestId, userId, actId, requestDate, requestStatus)
	VALUES (100006, 100002, 10002, CURDATE(), 'Approved');





INSERT INTO clubNews(newsId, newsTitle, newsContent, newsCategory, effectiveDate, releaseDate, userId, clubId)
	VALUES(200001, 'Ready for the Movie Night?', 'What¡¯s better than the smell of freshly popped popcorn, a comfy spot to cuddle, and an entertaining movie? Nothing! That¡¯s why fundraising with a family movie night at your local church or school is an easy way to raise much needed money. Actually, it¡¯s as easy as turning the lights down and letting the movie credits roll. We get a lot of queries for fundraisers that could work for a wide range of ages and community groups. Well, everyone enjoys a good movie. And a movie marathon fundraiser is an inexpensive way to raise funds, a great way to get the people in your community together, and also an acceptable activity for your next school or church fundraiser. Now, depending on your audience, you can organize a small movie night in your church recreational area, or a full-fledged movie marathon in a local theater or drive-in. In fact, most movie theaters and local drive-ins will work with groups to arrange special functions such as an overnight movie marathon or a double feature.', 'Test', CURDATE(), CURDATE(), 100001, 1001);

INSERT INTO clubNews(newsId, newsTitle, newsContent, newsCategory, effectiveDate, releaseDate, userId, clubId)
	VALUES(200002, 'Music Festival', 'There¡¯s a whiff of something in the air among summer festival organizers and it isn¡¯t boundless overconfidence. Kitchener¡¯s classic rock Big Music Fest was cancelled after one successful year and one that almost broke the bank when crowds didn¡¯t show up. Toronto¡¯s indie punk Riot Fest scrapped its Toronto stopover this year; the Warped Tour skipped Canada and Kingston¡¯s Wolfe Island Music Festival went on hiatus. As the festival climate shifts in radically new directions, even Guelph¡¯s bulletproof Hillside Festival is showing signs of vulnerability. ¡°Hillside is in the middle of the most perfect and imperfect storm right now,¡± noted artistic director Sam Baijal two weeks before the revered Guelph mainstay unveils its atmosphere of peace, love and indie music July 22 to 24 at Guelph Lake. Perfect: it¡¯s a festival frenzy out there, with more headliners on the road trying to recoup lost bucks from depleted album sales, more government funding for festival startups and more choice for the average schlub tired of sitting in his parents¡¯ basement composing angry tweets on his smartphone. Imperfect: Too many festivals, including one ¡ª WayHome Music & Arts Festival near Barrie ¡ª on the same weekend as Hillside, mining similar musical turf. Something has to give. ¡°It¡¯s diluted the market,¡± notes Baijal, adding that Southern Ontario is the most populated region in Canada. ¡°There¡¯s just tons of them.¡± He and Hillside¡¯s executive director, Marie Zimmerman, paint a picture of an indie music store that caters to loyal patrons, cultivates community spirit and boasts a word-of-mouth reputation for ethics and environmentalism. And then Walmart moves in. Hillside is perhaps the most dramatic example of what can happen when a gold rush mentality transforms a sensible business model into an ants-in-its-pants feeding frenzy. Celebrating its 33rd year with the kind of intensely loyal following normally associated with religious cults and followers of Charles Manson ¡ª I say this with affection ¡ª the Little Festival That Could finds itself in danger of being unceremoniously gobbled up by a corporate Goliath belching superstars 90 minutes down the highway between Barrie and Orillia.', 'Test', CURDATE(), CURDATE(), 100001, 1001);

INSERT INTO clubNews(newsId, newsTitle, newsContent, newsCategory, effectiveDate, releaseDate, userId, clubId)
	VALUES(200003, 'Charity Activity', 'Every year, millions of people donate money to charitable and non-profit organizations. By contributing financially to organizations and groups that support causes dear to their heart, donors want to contribute to the well-being of their fellow citizens or advance principles and values that they believe in. In recognition of the difference these donations can make in the community, governments provide income tax credits to encourage giving by taxpayers or match the amount donated by individuals in certain cases.

Sources of funding for charitable and non-profit organizations vary significantly according to the particular sector, each receiving greater or lesser levels of support in the form of government subsidies and grants, corporate donations, foundation grants, etc. Despite this diversity, almost all organizations count on individual donations to fulfil their mission and achieve their objectives. In that regard, gaining a better understanding of donors and their motivations can help organizations to make informed decisions.

This article looks at different aspects of charitable giving by Canadians in 2010. First, it provides information about donors and donations, comparing them with those in 2007. It also profiles the types of organizations that received the largest amounts of donations, distinguishing between religious and other types of organizations. People who give to religious organizations differ in some respects from those who give to non-religious ones.

The last section looks at what motivates people to donate and the reasons they cite for not giving more, including things that may have bothered them when they were approached. This information is important to many non-profit organizations that aim to improve their practices in such a way that donors have confidence in them and continue to give.', 'Test', CURDATE(), CURDATE(), 100001, 1001);

INSERT INTO clubNews(newsId, newsTitle, newsContent, newsCategory, effectiveDate, releaseDate, userId, clubId)
	VALUES(200004, 'Game Night', 'Family Game Night is a television series based on Hasbros family of board games and EAs video game franchise of the same name. The show is hosted by Todd Newton. Burton Richardson announced for the first two seasons, until he was replaced by Stacey J. Aswad for the third season, and then Andrew Kishino beginning in the fourth season. The 60-minute program debuted on October 10, 2010 on the new channel, The Hub, formerly Discovery Kids (later Discovery Family on October 13, 2014); it was previewed on October 9, 2010 on its sister channel, TLC. Seasons 1 through 3 each contained between 20 and 30 episodes. Seasons 4 and 5 each contained 15 episodes. Season two premiered on Friday, September 2, 2011, and new games were added. The games added to the second season included Cranium Brain Breaks (which replaced Guess Who? as the opening toss-up game), Green Scream, Ratuki Go-Round, Simon Flash, Operation Sam Dunk, Trouble Pop Quiz, and Spelling Bee.
On June 19, 2012 Family Game Night was renewed for a third season by The Hub, which premiered on September 23, 2012. On July 9, 2012, it was announced that Family Game Night was one of four original series from The Hub that won the CINE Golden Eagle Award for high quality production and storytelling.', 'Test', CURDATE(), CURDATE(), 100001, 1001);