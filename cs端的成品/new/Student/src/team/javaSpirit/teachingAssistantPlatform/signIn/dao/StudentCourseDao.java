package team.javaSpirit.teachingAssistantPlatform.signIn.dao;

import java.sql.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import team.javaSpirit.teachingAssistantPlatform.entity.Record;
import team.javaSpirit.teachingAssistantPlatform.entity.Students;
import team.javaSpirit.teachingAssistantPlatform.entity.Studentstatus;
import team.javaSpirit.teachingAssistantPlatform.entity.Times;
import team.javaSpirit.teachingAssistantPlatform.util.HibernateUtil;

public class StudentCourseDao {

	/**
	 * <p>
	 * Title: allCourse
	 * </p>
	 * <p>
	 * Description: 通过学号，查询本学生所上的所有课程号。
	 * </p>
	 * 
	 * @param sid 学号
	 * @return 所有的课程号
	 */
	public List<Integer> allCourseBysid(String sid) {
		Session session = HibernateUtil.getSession();
		Query q = session.createQuery("select classin.class_id from StudentClass where student.sid=?");
		q.setParameter(0, sid);
		return q.list();
	}

	/**
	 * <p>
	 * Title: allTimeBycid
	 * </p>
	 * <p>
	 * Description: 通过课程号，查到这门课的具体上课时间。
	 * </p>
	 * 
	 * @param class_id 课程班级id
	 * @return 所有的时间
	 */
	public List<Times> allTimeBycid(int class_id) {
		Session session = HibernateUtil.getSession();
		Query q = session.createQuery("from Times where classin.class_id=?");
		q.setParameter(0, class_id);
		return q.list();
	}

	/**
	 * <p>
	 * Title: getStartTime
	 * </p>
	 * <p>
	 * Description: 返回开学日期。
	 * </p>
	 * 
	 * @return
	 */
	public Date getStartTime() {
		Session session = HibernateUtil.getSession();
		Query q = session.createQuery("select time_begin from Term where status=1");
		return (Date) q.uniqueResult();
	}

	/**
	 * <p>
	 * Title: getBeginTime
	 * </p>
	 * <p>
	 * Description: 通过id,查找相对应的上课的开始时间。
	 * </p>
	 * 
	 * @param node_id id
	 * @return 上课的开始时间
	 */
	public String getBeginTime(int node_id) {
		Session session = HibernateUtil.getSession();
		Query q = session.createQuery("select start_time from NodeNumber where node_id=?");
		q.setParameter(0, node_id);
		return (String) q.uniqueResult();
	}

	/**
	 * <p>
	 * Title: insertRecord
	 * </p>
	 * <p>
	 * Description: 插入一条签到数据。
	 * </p>
	 * 
	 * @param r 签到表的对象
	 */
	public void insertRecord(Record r) {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		session.save(r);
		session.getTransaction().commit();
	}

	/**
	 * <p>Title: changeStudentStatus</p>
	 * <p>Description: 签到成功，修改状态</p>
	 * @param sid 学号
	 */
	public void changeStudentStatus(String sid) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		Studentstatus studentstatus = session.get(Studentstatus.class, sid);
		studentstatus.setRecord_status(1);
		tx.commit();
	}

}
