update t_block b1
set b1.guid = (
select concat( 
		concat(
			concat(
				concat(
					concat('/', pd.name) , '/'
					), pn.name
				), '#' 
			) , b.guid
		) 
from t_block b
left join t_processing ping
	on b.processing = ping.id
left join t_processing_name pn
	on ping.name = pn.id
left join t_primary_dataset pd
	on ping.primary_dataset = pd.id
where b.id = b1.id
);

select id from t_block;

select * from t_block;

for i in $(cat blocks.txt) ; do echo "update t_block set guid = '`uuidgen`' where id = $i;" ;   done



for i in $(cat block.txt) ; do
echo "
update t_block b1
set b1.guid = (
select concat(
                concat(
                        concat(
                                concat(
                                        concat('/', pd.name) , '/'
                                        ), pn.name
                                ), '#'
                        ) , '`uuidgen`'
                )
from t_block b
left join t_processing ping
        on b.processing = ping.id
left join t_processing_name pn
        on ping.name = pn.id
left join t_primary_dataset pd
        on ping.primary_dataset = pd.id
where b.id = b1.id
and b.id = $i
)
where b1.id = $i; "
done




update t_file f1
set f1.LOGICAL_NAME = (
  select concat ('/store' , f2.LOGICAL_NAME)
  from t_file f2
      where
          f1.LOGICAL_NAME = f2.LOGICAL_NAME )
where
f1.LOGICAL_NAME not like '%/store%';
