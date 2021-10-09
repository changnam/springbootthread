 drop table if exists blogs CASCADE ;
    
    create table blogs (
       blogId integer not null,
        blogCreator varchar(255),
        blogTitle varchar(255),
        yearOfPost integer not null,
        primary key (blogId)
    );