package cn.gyyx.elves.console.domain;

import java.sql.ResultSet;
import java.util.List;

public class Page {
	
	private int totalPage = 0;//鎬婚〉鐮�
	private int totalRows = 0;//鎬昏褰曟暟
	private int currentPage = 1;//褰撳墠椤电爜
	private int pageSize = 10;//姣忛〉璁板綍鏁�
	private List list = null;//鏁版嵁鍒楄〃
	private String sql = null;//鏌ヨ璇彞
	private ResultSet resultSet = null;
	private static int defaultPageSize = 10;//姣忛〉榛樿鏉℃暟
	
	
	
	public int getTotalPage() {
		this.totalPage = (int) Math.ceil(this.totalRows / this.pageSize) + (this.totalRows % this.pageSize > 0 ? 1:0);
		return this.totalPage;
	}
	
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	/**
	 * 鑾峰彇褰撳墠鏌ヨ鎬昏褰曟暟
	 * @return int totalRows 鎬昏褰曟暟
	 */
	public int getTotalRows() {
		return this.totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	public int getCurrentPage() {
		return this.currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return this.pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		if(pageSize<=0){
			this.pageSize = this.defaultPageSize;
		}
	}
	public List getList() {
		return this.list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public String getSql() {
		return this.sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public ResultSet getResultSet() {
		return this.resultSet;
	}
	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

    @Override
    public String toString() {
        return "Page [totalPage=" + totalPage + ", totalRows="
                + totalRows + ", currentPage=" + currentPage + ", pageSize="
                + pageSize + ", list=" + list + ", sql=" + sql + ", resultSet="
                + resultSet + "]";
    }

}
