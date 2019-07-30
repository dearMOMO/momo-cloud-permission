<@pp.dropOutputFile />
<#assign count=0 />
<#list dalgen.pagings as paging>

    <#assign count=count+1 />
    <#if count gt 1 >
        <#break>
    </#if>

    <@pp.changeOutputFile name = "/main/java/${paging.baseClassPath}/BasePage.java" />
package ${paging.basePackageName};

import java.util.List;
import java.util.ArrayList;

/**
 * 用于分页的工具类
 */
public class BasePage<T> {

    private List<T> datas           = new ArrayList<>();         //对象记录结果集
    private int     total           = 0;    // 总记录数
    private int     limit           = 20;   // 默认每页显示记录数
    private int     pageNos         = 1;    // 总页数
    private int     currPageNo      = 1;    // 当前页

    private boolean isFirstPage     = false; //是否为第一页
    private boolean isLastPage      = false; //是否为最后一页
    private boolean hasPreviousPage = false; //是否有前一页
    private boolean hasNextPage     = false; //是否有下一页

    private int     navigatePages   = 8;    //导航页码数
    private int[]   navigatePageNos;        //所有导航页号

    private void init() {
        this.pageNos = (this.total - 1) / this.limit + 1;

        //根据输入可能错误的当前号码进行自动纠正
        if (currPageNo < 1) {
            this.currPageNo = 1;
        } else if (currPageNo > this.pageNos) {
            this.currPageNo = this.pageNos;
        } else {
            this.currPageNo = currPageNo;
        }

        //基本参数设定之后进行导航页面的计算
        calcNavigatePageNumbers();

        //以及页面边界的判定
        judgePageBoudary();
    }

    /**
     * 计算导航页
     */
    private void calcNavigatePageNumbers() {
        //当总页数小于或等于导航页码数时
        if (pageNos <= navigatePages) {
            navigatePageNos = new int[pageNos];
            for (int i = 0; i < pageNos; i++) {
                navigatePageNos[i] = i + 1;
            }
        } else { //当总页数大于导航页码数时
            navigatePageNos = new int[navigatePages];
            int startNum = currPageNo - navigatePages / 2;
            int endNum = currPageNo + navigatePages / 2;

            if (startNum < 1) {
                startNum = 1;
                //(最前navigatePages页
                for (int i = 0; i < navigatePages; i++) {
                    navigatePageNos[i] = startNum++;
                }
            } else if (endNum > pageNos) {
                endNum = pageNos;
                //最后navigatePages页
                for (int i = navigatePages - 1; i >= 0; i--) {
                    navigatePageNos[i] = endNum--;
                }
            } else {
                //所有中间页
                for (int i = 0; i < navigatePages; i++) {
                    navigatePageNos[i] = startNum++;
                }
            }
        }
    }

    /**
     * 判定页面边界
     */
    private void judgePageBoudary() {
        isFirstPage = currPageNo == 1;
        isLastPage = currPageNo == pageNos && currPageNo != 1;
        hasPreviousPage = currPageNo > 1;
        hasNextPage = currPageNo < pageNos;
    }

    /**
     * 得到数据
     * 
     * @return
     */
    public List<T> getDatas() {
        return datas;
    }

    /**
     * 设置数据
     * 
     * @param datas
     */
    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    /**
     * 得到记录总数
     *
     * @return {int}
     */
    public int getTotal() {
        return total;
    }

    /**
     * 设置总记录数
     * 
     * @param total
     */
    public void setTotal(int total) {
        this.total = total;
        init();
    }

    /**
     * 得到每页显示多少条记录
     *
     * @return {int}
     */
    public int getLimit() {
        return limit;
    }

    /**
     * 设置每页多少记录
     * 
     * @param limit
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * 设置导航线上几页
     * 
     * @param navigatePages
     */
    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    /**
     * 得到页面总数
     *
     * @return {int}
     */
    public int getPageNos() {
        return pageNos;
    }

    /**
     * 得到当前页号
     *
     * @return {int}
     */
    public int getCurrPageNo() {
        return currPageNo;
    }

    /**
     * 得到所有导航页号
     *
     * @return {int[]}
     */
    public int[] getNavigatePageNos() {
        return navigatePageNos;
    }

    public boolean isFirstPage() {
        return isFirstPage;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public boolean hasPreviousPage() {
        return hasPreviousPage;
    }

    public boolean hasNextPage() {
        return hasNextPage;
    }

    /**
     * 设置当前行
     * @param currPageNo
     */
    public void setCurrPageNo(int currPageNo){
        if(currPageNo==0){
            this.currPageNo =1;
        }else {
            this.currPageNo = currPageNo;
        }
    }

    /**
    * 得到开始行
    */
    public int getStartRow(){
        return (this.currPageNo-1)*this.limit;
    }

                public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[").append("total=").append(total).append(",pageNos=").append(pageNos)
                .append(",currPageNo=").append(currPageNo).append(",limit=").append(limit)
                .append(",isFirstPage=").append(isFirstPage).append(",isLastPage=")
                .append(isLastPage).append(",hasPreviousPage=").append(hasPreviousPage)
                .append(",hasNextPage=").append(hasNextPage).append(",navigatePageNos=");
        int len = navigatePageNos.length;
        if (len > 0)
            sb.append(navigatePageNos[0]);
        for (int i = 1; i < len; i++) {
            sb.append(" " + navigatePageNos[i]);
        }
        sb.append(",datas.size=" + datas.size());
        sb.append("]");
        return sb.toString();
    }
}
</#list>
