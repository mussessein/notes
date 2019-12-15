# 牛客SQL题目

1. 查找最晚入职员工的所有信息

   ```sql
   select * 
   from 
   employees
   where 
   hire_date = (select max(hire_date) from employees);
   ```

2. 查找入职员工时间排名倒数第3的员工所有信息

   ```sql
   select * 
   from 
   employees 
   order by 
   hire_date 
   desc --降序
   limit 2,1;--从3项，偏移1项，即第3项
   ```

3. 查找各个部门当前(to_date='9999-01-01')的领导的当前薪水详情以及其对应部门编号dept_no

   ```sql
   select s.*,d.dept_no
   from salaries as s
   join dept_manager as d
   on d.emp_no=s.emp_no
   where s.to_date='9999-01-01'
   and d.to_date='9999-01-01';
   ```

4. 查找所有已经分配部门的员工的last_name和first_name

   ```sql
   select e.last_name,e.first_name,d.dept_no
   from employees as e
   join dept_emp as d
   on e.emp_no = d.emp_no;
   ```

5. 查找所有员工的last_name和first_name以及对应部门编号dept_no，也包括展示没有分配具体部门的员工

   ```sql
   select e.last_name,e.first_name,d.dept_no
   from employees as e
   left join dept_emp as d--坐表为主，右表补充，为空补NULL
   on e.emp_no = d.emp_no;
   ```

6. 查找所有员工**入职时候**的薪水情况，给出emp_no以及salary， 并按照emp_no进行逆序

   ```sql
   select e.emp_no,s.salary
   from salaries as s
   join employees as e
   where e.emp_no = s.emp_no
   and e.hire_date = s.from_date
   order by
   e.emp_no
   desc;
   ```

7. 查找薪水涨幅超过15次的员工号emp_no以及其对应的涨幅次数t

   count( )：统计记录的条数

   还需要group by  emp_no将每个员工的记录显示在一条记录中

   ```sql
   select emp_no,count(emp_no) as t
   from salaries
   group by
   emp_no
   having t > 15;
   ```

8. 找出所有员工当前(to_date='9999-01-01')具体的薪水salary情况，对于相同的薪水只显示一次,并按照逆序显示

   distinct：去除重复

   ```sql
   select distinct salary-- 去除重复
   from salaries
   where to_date = '9999-01-01'
   order by
   salary
   desc;
   ```

9. 获取所有部门当前manager的当前薪水情况，给出dept_no, emp_no以及salary，当前表示to_date='9999-01-01'

   ```sql
   select d.dept_no,d.emp_no,s.salary
   from dept_manager as d
   join salaries as s
   on d.emp_no=s.emp_no
   where d.to_date='9999-01-01'
   and s.to_date='9999-01-01';
   ```

10. 获取所有非manager的员工emp_no

    ```sql
    select emp_no
    from employees
    where emp_no not in (select emp_no from dept_manager);
    ```

11. 获取所有员工当前的manager，如果当前的manager是自己的话结果不显示，当前表示to_date='9999-01-01'。

    ```sql
    select e.emp_no,m.emp_no as manager_no
    from dept_emp as e
    join dept_manager as m
    on e.dept_no=m.dept_no
    where e.emp_no <> m.emp_no -- 当前manager是自己不显示，<>不等于
    and e.to_date='9999-01-01'
    and m.to_date='9999-01-01';
    ```

12. 获取所有部门中当前员工薪水最高的相关信息，给出dept_no, emp_no以及其对应的salary

    ```sql
    select d.dept_no,d.emp_no,max(s.salary)
    from dept_emp as d
    join salaries as s
    on d.emp_no = s.emp_no
    group by
    d.dept_no;
    ```

13. 从titles表获取按照title进行分组，每组个数大于等于2，给出title以及对应的数目t。

    ```sql
    select title,count(title) as t
    from titles
    group by
    title
    having t >= 2;
    ```

14. 查找employees表所有emp_no为奇数，且last_name不为Mary的员工信息，并按照hire_date逆序排列

    ```sql
    select * from employees
    where emp_no%2 =1
    and last_name <> 'Mary'
    order by
    hire_date
    desc;
    ```

15. 统计出当前各个title类型对应的员工当前（to_date='9999-01-01'）薪水对应的平均工资。结果给出title以及平均工资avg。

    ```sql
    select t.title,avg(s.salary)
    from titles as t
    join salaries as s
    on t.emp_no = s.emp_no
    where s.to_date='9999-01-01'
    and t.to_date='9999-01-01'
    group by
    t.title;
    ```

16. 获取当前（to_date='9999-01-01'）薪水第二多的员工的emp_no以及其对应的薪水salary

    ```sql
    select emp_no,salary from salaries
    where to_date='9999-01-01'
    order by
    salary
    desc
    limit 1,1;	-- 降序，取第二个
    ```

17. 查找当前薪水(to_date='9999-01-01')排名第二多的员工编号emp_no、薪水salary、last_name以及first_name，不准使用order by

    ```sql
    select e.emp_no,s.salary,e.last_name,e.first_name
    from employees as e
    join salaries as s
    on e.emp_no=s.emp_no
    where s.to_date='9999-01-01'
    order by
    s.salary
    desc
    limit 1,1;
    ```

    不适用order by：需要用嵌套select和max结合

    ```sql
    select e.emp_no,max(salary) ,e.last_name,e.first_name --这里用max(salary)
    from  salaries as s,employees as e
    where s.emp_no = e.emp_no
    and salary<  -- 小于最大薪水中的最大，就是第二大
    (
    select max(salary)
    from salaries
    where to_date = '9999-01-01'
    );
    ```

18. 查找所有员工的last_name和first_name以及对应的dept_name，也包括暂时没有分配部门的员工

    **双左连接**

    ```sql
    select e.last_name,e.first_name,de.dept_name
    from employees as e
    left join dept_emp as d
    on e.emp_no=d.emp_no
    left join departments as de
    on d.dept_no=de.dept_no;
    ```

19. 查找员工编号emp_no为10001其自入职以来的薪水salary涨幅值growth

    通过入职时间来排序查询：

    如果直接用薪水最大值-最小值，有可能最后一次是降薪。

    ```sql
    SELECT ( 
    (SELECT salary FROM salaries WHERE emp_no = 10001 ORDER BY to_date DESC LIMIT 1) -
    (SELECT salary FROM salaries WHERE emp_no = 10001 ORDER BY to_date ASC LIMIT 1)
    ) AS growth
    ```

20. 查找所有员工自入职以来的薪水涨幅情况，给出员工编号emp_no以及其对应的薪水涨幅growth，并按照growth进行升序

    ```sql
    --将两个查询的结果，当成两个表，进行join
    select S2.emp_no, (S2.salary-S1.salary) as growth
    from 
    (select e.emp_no,s.salary 
     from employees as e 
     join salaries as s 
     on e.emp_no=s.emp_no 
     and e.hire_date=s.from_date) as S1  --所有员工的入职工资
    join 
    (select e.emp_no,s.salary 
     from employees as e 
     join salaries as s 
     on e.emp_no=s.emp_no 
     and s.to_date='9999-01-01') as S2   --所有员工的当前工资
    on S1.emp_no = S2.emp_no
    order by growth;
    ```

21. 统计各个部门对应员工涨幅的次数总和，给出部门编码dept_no、部门名称dept_name以及次数sum

    ```sql
    select dp.dept_no,dp.dept_name,count(s.emp_no) as sum
    from departments as dp
    join dept_emp as de on de.dept_no = dp.dept_no
    join salaries as s
    on de.emp_no = s.emp_no
    group by
    de.dept_no;
    ```

22. 对所有员工的当前(to_date='9999-01-01')薪水按照salary进行按照1-N的排名，相同salary并列且按照emp_no升序排列

    ```sql
    SELECT s1.emp_no, s1.salary, COUNT(DISTINCT s2.salary) AS rank
    FROM salaries AS s1, salaries AS s2
    WHERE s1.to_date = '9999-01-01'  AND s2.to_date = '9999-01-01' AND s1.salary <= s2.salary
    GROUP BY s1.emp_no
    ORDER BY s1.salary DESC, s1.emp_no ASC
    ```

23. 获取所有非manager员工当前的薪水情况，给出dept_no、emp_no以及salary ，当前表示to_date='9999-01-01'

    ```sql
    select de.dept_no,de.emp_no,s.salary
    from dept_emp as de
    join salaries as s
    on de.emp_no=s.emp_no
    where de.emp_no not in (select emp_no from dept_manager where to_date='9999-01-01')
    and de.to_date='9999-01-01'
    and s.to_date='9999-01-01';
    ```

24. 获取员工其当前的薪水比其manager当前薪水还高的相关信息，当前表示to_date='9999-01-01',

    ```sql
    select S1.emp_no as emp_no,S2.emp_no as manager_no,S1.salary as emp_salary,S2.salary as manager_salary
    from
    (select s.salary,e.emp_no,e.dept_no from salaries as s join dept_emp as e on e.emp_no = s.emp_no and s.to_date='9999-01-01') as S1,
    (select s.salary,m.emp_no,m.dept_no from salaries as s join dept_manager as m on m.emp_no = s.emp_no and s.to_date='9999-01-01') as S2
    where S1.dept_no = S2.dept_no and S1.salary > S2.salary
    ```

25. 汇总各个部门当前员工的title类型的分配数目，结果给出部门编号dept_no、dept_name、其当前员工所有的title以及该类型title对应的数目count

    ```sql
    select d.dept_no,d.dept_name,t.title,count(t.title) as count
    from titles as t
    join dept_emp as e
    on e.emp_no = t.emp_no and e.to_date='9999-01-01' and t.to_date='9999-01-01'
    join
    departments as d
    on d.dept_no = e.dept_no
    group by d.dept_no,T.title
    ```

26. 给出每个员工每年薪水涨幅超过5000的员工编号emp_no、薪水变更开始日期from_date以及薪水涨幅值salary_growth，并按照salary_growth逆序排列。

    提示：在sqlite中获取datetime时间对应的年份函数为strftime('%Y', to_date)

    ```sql
    select s2.emp_no,s2.from_date,s2.salary-s1.salary as salary_growth
    from salaries as s1,salaries as s2
    where s1.emp_no = s2.emp_no
    and salary_growth > 5000
    and (strftime("%Y",s2.to_date) - strftime("%Y",s1.to_date) = 1
    OR strftime("%Y",s2.from_date) - strftime("%Y",s1.from_date) = 1)
    order by salary_growth desc
    ```

27. 查找描述信息中包括robot的电影对应的分类名称以及电影数目，而且还需要该分类对应电影数量>=5部

    ```sql
    SELECT c.name AS name, COUNT(f.film_id) AS amount
    FROM film AS f, film_category AS fc, category AS c,
    (SELECT category_id FROM film_category GROUP BY category_id HAVING COUNT(category_id) >= 5) AS cc
    WHERE f.description LIKE '%robot%'
    AND f.film_id = fc.film_id
    AND fc.category_id = c.category_id
    AND c.category_id = cc.category_id
    ```

28. 使用join查询方式找出没有分类的电影id以及名称

    ```sql
    select f.film_id,f.title
    from film as f
    left join 
    film_category as fc
    on fc.film_id = f.film_id
    where fc.category_id is null
    ```

29. 使用子查询的方式找出属于Action分类的所有电影对应的title,description

    ```sql
    -- 非子查询
    select f.title,f.description
    from film as f,film_category as fc,
    (select category_id from category where name = 'Action') as ac
    where f.film_id = fc.film_id and fc.category_id = ac.category_id
    ```

    ```sql
    --join
    select f.title,f.description
    from film as f 
    inner join 
    film_category as fc 
    on f.film_id = fc.film_id
    inner join 
    category as c 
    on c.category_id = fc.category_id
    where c.name = 'Action';
    ```

    ```sql
    --子查询
    select f.title,f.description
    from film as f where f.film_id in (select fc.film_id from film_category as fc where fc.category_id in (select c.category_id from category as c where name ='Action'))
    ```

30. explain ，此题毫无意义 = =

31. 将employees表的所有员工的last_name和first_name拼接起来作为Name，中间以一个空格区分

    ```sql
    select concat(concat(last_name," "),first_name) as name from employees;
    select CONCAT(last_name," ",first_name) as name from employees
    select last_name||" "||first_name as name  from employees
    ```

32. 创建一个actor表

    ```sql
    create table actor(
        actor_id smallint(5) not null,
        first_name varchar(45) not null,
        last_name varchar(45) not null,
        last_update timestamp not null DEFAULT (datetime('now','localtime')),
        PRIMARY KEY(actor_id)
    );
    ```

33. 对于表actor批量插入数据

    ```sql
    insert into actor values(1,'PENELOPE','GUINESS','2006-02-15 12:34:33'),(2,'NICK','WAHLBERG','2006-02-15 12:34:33');
    ```

34. 对于表actor批量插入如下数据,如果数据已经存在，请忽略，不使用replace操作

    对于Mysql 不用加 or

    ```sql
    INSERT [OR] IGNORE INTO actor values(3,'ED','CHASE','2006-02-15 12:34:33');
    ```

35. 创建一个actor_name表，从actor表中导入数据

    ```sql
    create table actor_name(
        first_name varchar(45) not null,
        last_name varchar(45) not null
    );
    INSERT INTO actor_name select first_name,last_name from actor;
    ```

36. 表actor结构创建索引

    对first_name创建唯一索引uniq_idx_firstname，对last_name创建普通索引idx_lastname

    ```sql
    create unique index uniq_idx_firstname on actor(first_name);
    create index idx_lastname on actor(last_name);
    ```

37. 针对actor表创建视图actor_name_view,并重新命名；

    ```sql
    create view actor_name_view (first_name_v,last_name_v) as
    select first_name,last_name from actor
    ```

38. 针对salaries表emp_no字段创建索引idx_emp_no，查询emp_no为10005, 使用强制索引

    ```sql
    create index idx_emp_no on salaries(emp_no);
    select * from salaries
    indexed by idx_emp_no
    where emp_no = '10005'
    ```

39. 添加列：last_update后面新增加一列名字为create_date, 类型为datetime, NOT NULL，默认值为'0000 00:00:00'

    ```sql
    ALTER table actor add create_date datetime not null default '0000-00-00 00:00:00' ALTER TABLE actor ADD create_date datetime NOT NULL DEFAULT '0000-00-00 00:00:00'
    ```

40. 构造一个触发器audit_log，在向employees_test表中插入一条数据的时候，触发插入相关的数据到audit中

    ```sql
    create trigger audit_log after insert on employees_test
    begin
         insert into audit values(new.id,new.name);
    end;
    ```

41. 

