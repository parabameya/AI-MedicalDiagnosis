
create table appendix
(
fever float,
nausea float,
vomiting float,
sleeping float,
urination float,
headache float,
muscle float,
rashes float,
cough float,
nose float
);

create table dengue
(
fever float,
nausea float,
vomiting float,
sleeping float,
urination float,
headache float,
muscle float,
rashes float,
cough float,
nose float
);

create table influenza
(
fever float,
nausea float,
vomiting float,
sleeping float,
urination float,
headache float,
muscle float,
rashes float,
cough float,
nose float
);

create table measles
(
fever float,
nausea float,
vomiting float,
sleeping float,
urination float,
headache float,
muscle float,
rashes float,
cough float,
nose float
);

create table pollen
(
fever float,
nausea float,
vomiting float,
sleeping float,
urination float,
headache float,
muscle float,
rashes float,
cough float,
nose float
);

create table count_no
(
appendix float not null,
dengue float not null,
influenza float not null,
measles float not null,
pollen float not null
);



select * from appendix

insert into dengue values(1,1,1,0,0,1,1,1,0,0);
insert into dengue values(1,1,1,0,0,1,1,0,0,0);
insert into dengue values(1,1,1,0,0,1,0,1,0,0);
insert into dengue values(1,1,0,0,0,1,0,0,0,0);
insert into dengue values(1,1,0,0,0,1,0,1,0,0);

select * from dengue


insert into influenza values(1,0,0,1,0,1,1,0,1,1);
insert into influenza values(1,0,0,0,0,1,1,0,1,0);
insert into influenza values(1,0,0,0,0,1,1,0,1,1);
insert into influenza values(1,0,0,0,0,1,1,0,1,1);
insert into influenza values(1,0,0,0,0,1,1,0,1,0);

select * from influenza

insert into measles values(1,0,0,0,0,0,0,1,1,1);
insert into measles values(1,0,0,1,0,0,0,1,1,1);
insert into measles values(1,0,0,1,0,1,0,1,1,1);
insert into measles values(1,0,0,0,0,1,0,1,1,1);
insert into measles values(1,0,0,0,0,0,0,1,1,1);

select * from measles

insert into pollen values(0,0,1,0,0,0,0,1,1,0);
insert into pollen values(0,0,1,0,0,0,0,1,1,0);
insert into pollen values(0,0,0,0,0,0,0,1,1,1);
insert into pollen values(0,0,0,0,0,0,0,1,1,0);
insert into pollen values(0,0,0,0,0,0,0,1,1,0);

select * from pollen

create VIEW probability_pollen AS 
(
select * from
(select (sum(fever)/count(*))as pollen_fever from pollen ) as a,
(select (sum(nausea)/count(*))as pollen_nausea from pollen ) as b,
(select (sum(vomiting)/count(*))as pollen_vomiting from pollen ) as c,
(select (sum(sleeping)/count(*))as pollen_sleeping from pollen ) as d,
(select (sum(urination)/count(*))as pollen_urination from pollen ) as e,
(select (sum(headache)/count(*))as pollen_headache from pollen ) as f,
(select (sum(muscle)/count(*))as pollen_muscle from pollen ) as g,
(select (sum(rashes)/count(*))as pollen_rashes from pollen ) as h,
(select (sum(cough)/count(*))as pollen_cough from pollen ) as i,
(select (sum(nose)/count(*))as pollen_nose from pollen ) as j
)

select * from probability_pollen

create VIEW probability_appendix AS 
(
select * from
(select (sum(fever)/count(*))as appendix_fever from appendix ) as a,
(select (sum(nausea)/count(*))as appendix_nausea from appendix ) as b,
(select (sum(vomiting)/count(*))as appendix_vomiting from appendix ) as c,
(select (sum(sleeping)/count(*))as appendix_sleeping from appendix ) as d,
(select (sum(urination)/count(*))as appendix_urination from appendix ) as e,
(select (sum(headache)/count(*))as appendix_headache from appendix ) as f,
(select (sum(muscle)/count(*))as appendix_muscle from appendix ) as g,
(select (sum(rashes)/count(*))as appendix_rashes from appendix ) as h,
(select (sum(cough)/count(*))as appendix_cough from appendix ) as i,
(select (sum(nose)/count(*))as appendix_nose from appendix ) as j
)

select * from probability_appendix

create VIEW probability_dengue AS 
(
select * from
(select (sum(fever)/count(*))as dengue_fever from dengue ) as a,
(select (sum(nausea)/count(*))as dengue_nausea from dengue ) as b,
(select (sum(vomiting)/count(*))as dengue_vomiting from dengue ) as c,
(select (sum(sleeping)/count(*))as dengue_sleeping from dengue ) as d,
(select (sum(urination)/count(*))as dengue_urination from dengue ) as e,
(select (sum(headache)/count(*))as dengue_headache from dengue ) as f,
(select (sum(muscle)/count(*))as dengue_muscle from dengue ) as g,
(select (sum(rashes)/count(*))as dengue_rashes from dengue ) as h,
(select (sum(cough)/count(*))as dengue_cough from dengue ) as i,
(select (sum(nose)/count(*))as dengue_nose from dengue ) as j
)

select * from probability_dengue

create VIEW probability_influenza AS 
(
select * from
(select (sum(fever)/count(*))as influenza_fever from influenza ) as a,
(select (sum(nausea)/count(*))as influenza_nausea from influenza ) as b,
(select (sum(vomiting)/count(*))as influenza_vomiting from influenza ) as c,
(select (sum(sleeping)/count(*))as influenza_sleeping from influenza ) as d,
(select (sum(urination)/count(*))as influenza_urination from influenza ) as e,
(select (sum(headache)/count(*))as influenza_headache from influenza ) as f,
(select (sum(muscle)/count(*))as influenza_muscle from influenza ) as g,
(select (sum(rashes)/count(*))as influenza_rashes from influenza ) as h,
(select (sum(cough)/count(*))as influenza_cough from influenza ) as i,
(select (sum(nose)/count(*))as influenza_nose from influenza ) as j
)

select * from probability_influenza

create VIEW probability_measles AS 
(
select * from
(select (sum(fever)/count(*))as measles_fever from measles ) as a,
(select (sum(nausea)/count(*))as measles_nausea from measles ) as b,
(select (sum(vomiting)/count(*))as measles_vomiting from measles ) as c,
(select (sum(sleeping)/count(*))as measles_sleeping from measles ) as d,
(select (sum(urination)/count(*))as measles_urination from measles ) as e,
(select (sum(headache)/count(*))as measles_headache from measles ) as f,
(select (sum(muscle)/count(*))as measles_muscle from measles ) as g,
(select (sum(rashes)/count(*))as measles_rashes from measles ) as h,
(select (sum(cough)/count(*))as measles_cough from measles ) as i,
(select (sum(nose)/count(*))as measles_nose from measles ) as j
)

select * from probability_measles

create view count_all as
(
	select count(*) as a from appendix 
	UNION ALL
	select count(*) from dengue 
	UNION ALL
	select count(*) from influenza
	UNION ALL
	select count(*) from measles
	UNION ALL
	select count(*) from pollen
)


select * from dengue