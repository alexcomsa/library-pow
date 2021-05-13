create table customer_model (
        id bigint generated by default as identity,
        email_address varchar(255) not null unique ,
        home_address varchar(255),
        name varchar(255) not null ,
        primary key (id)
    );

create table book_inventory (
        library_inventory_number bigint generated by default as identity,
        author varchar(255),
        borrowed boolean not null,
        isbn bigint,
        publisher varchar(255),
        title varchar(255),
        primary key (library_inventory_number));

create index insb_index on book_inventory (isbn);

create table borrowed_list (
        id bigint generated by default as identity,
        borrow_date timestamp,
        return_date timestamp,
        book_library_inventory_number bigint,
        customer_id bigint,
        primary key (id));

 alter table borrowed_list
       add constraint FK_book_library_inventory_number
       foreign key (book_library_inventory_number)
       references book_inventory;

alter table borrowed_list
       add constraint FK_customer_id
       foreign key (customer_id)
       references customer_model;



