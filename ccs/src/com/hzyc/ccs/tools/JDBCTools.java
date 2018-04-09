package com.hzyc.ccs.tools;



import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class JDBCTools {
	
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
    private PreparedStatement ps = null;
	private void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3308/ccs?autoReconnect=true", "root", "mysql");

			stmt = conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	public int update(String sql) {
		int rows = 0;
		try {
			connect();
			rows = stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return rows;
	}
	
	public ArrayList<HashMap<String, String>> find(String sql) {
		ArrayList<HashMap<String, String>> rsList = new ArrayList<HashMap<String, String>>();
		try {
			connect();
			rs = stmt.executeQuery(sql);
			System.out.println("Query SQL:"+sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				HashMap<String, String> oneMap = new HashMap<String, String>();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					oneMap.put(rsmd.getColumnName(i), rs.getString(rsmd.getColumnName(i)));
				}
				rsList.add(oneMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return rsList;
	}
	
	public boolean batch(ArrayList<String> sqls)  {
		try {
			connect();
			conn.setAutoCommit(false);
			for (int i = 0; i < sqls.size(); i++) {
				System.out.println("Batch SQL : "+sqls.get(i));
				stmt.addBatch(sqls.get(i));
			}
			stmt.executeBatch();
			conn.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				WriteLog.write(e1, "执行回滚异常");
			}
			WriteLog.write(e, "执行批量异常");
			return false;
		} finally {
			close();
		}
	}
	public boolean batchnorollback(ArrayList<String> sqls){
		try {
			connect();
			for (int i = 0; i < sqls.size(); i++) {
				System.out.println("Batch SQL : "+sqls.get(i));
				stmt.addBatch(sqls.get(i));
			}
			stmt.executeBatch();
		} catch (Exception e) {
			e.printStackTrace();
			WriteLog.write(e, "批量异常");
		} finally {
			close();
		}
		return true;
	}
	/**
	 * sql异常直接回滚
	 * */
	public synchronized boolean batchWithRollback(ArrayList<String> sqls)  {
		boolean bBatch = false;
		try {
			connect();
			conn.setAutoCommit(false);
			for (int i = 0; i < sqls.size(); i++) {
				System.out.println("Batch SQL : "+sqls.get(i));
				stmt.addBatch(sqls.get(i));
			}
			stmt.executeBatch();
			bBatch = true;
		} catch (Exception e) {
			e.printStackTrace();
			WriteLog.write(e, "执行批量异常");
			System.out.println("sql执行异常，回滚数据中...");
			try {
				conn.rollback();
			} catch (Exception e2) {
				System.out.println("[HZYC] ------------ roll back error : " + e2.getMessage());
				//write file 
				e2.printStackTrace();
				WriteLog.write(e2, "回滚异常");
			}
		} finally {
			commit();
			close();
		}
		return bBatch;
	}
	
	private void commit() {
		try {
			conn.commit();
		} catch (Exception e) {
			System.out.println("[HZYC] ------------ commit error : " + e.getMessage());
			e.printStackTrace();
			WriteLog.write(e, "事务提交异常");
		}
	}
	//向数据库中插入图片
	public void readImage2DB(String path, String imgName, Integer userid) throws IOException{
		 FileInputStream in = null;
        try {
            in = ImageUtil.readImage(path);
            conn = DBUtil.getConn();
            String sql = "update users set img =?, img_name=? where userid='"+userid+"'";
            ps = conn.prepareStatement(sql);
            ps.setBinaryStream(1, in, in.available());
            ps.setString(2, imgName);
            int count = ps.executeUpdate();
            if (count > 0) {
                System.out.println("插入成功！");
            } else {
                System.out.println("插入失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConn(conn);
            in.close();
            if (null != ps) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
	//向数据库中插入图片
	public void readImage2DB1(String path, String imgName, Integer userid) throws IOException{
		 FileInputStream in = null;
        try {
            in = ImageUtil.readImage(path);
            conn = DBUtil.getConn();
            String sql = "update users set img1 =?, img_name1=? where userid='"+userid+"'";
            ps = conn.prepareStatement(sql);
            ps.setBinaryStream(1, in, in.available());
            ps.setString(2, imgName);
            int count = ps.executeUpdate();
            if (count > 0) {
                System.out.println("插入成功！");
            } else {
                System.out.println("插入失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConn(conn);
            in.close();
            if (null != ps) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
	 // 读取数据库中图片
    public void readDB2Image(String path,int userid) throws IOException {
        String targetPath = path;
        InputStream in = null;
        try {
            conn = DBUtil.getConn();
            String sql = "select * from users where userid ='"+userid+"'";
            ps = conn.prepareStatement(sql);
            //ps.setInt(1, 1);
            rs = ps.executeQuery();
            while (rs.next()) {
                in = rs.getBinaryStream("img");
                //如果图片不存在，则不进行读写
                if (in != null && in.available() > 0) {
                	ImageUtil.readBin2Image(in, targetPath);
                }
               
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	if(in != null){
       		 in.close();
        	}
            DBUtil.closeConn(conn);
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    // 读取数据库中图片
    public void readDB2Image1(String path,int userid) throws IOException {
        String targetPath = path;
        InputStream in = null;
        try {
            conn = DBUtil.getConn();
            String sql = "select * from users where userid ='"+userid+"'";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                in = rs.getBinaryStream("img1");
                //如果图片不存在，则不进行读写
                if (in != null && in.available() > 0) {
                	ImageUtil.readBin2Image(in, targetPath);
                }
               
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	if(in != null){
        		 in.close();
        	}
            DBUtil.closeConn(conn);
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }
} 




