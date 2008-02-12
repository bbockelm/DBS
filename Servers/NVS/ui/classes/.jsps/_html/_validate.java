package _html;

import oracle.jsp.runtime.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import oracle.jsp.el.*;
import javax.servlet.jsp.el.*;


public class _validate extends com.orionserver.http.OrionHttpJspPage {


  // ** Begin Declarations


  // ** End Declarations

  public void _jspService(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException, ServletException {

    response.setContentType( "text/html;charset=UTF-8");
    /* set up the intrinsic variables using the pageContext goober:
    ** session = HttpSession
    ** application = ServletContext
    ** out = JspWriter
    ** page = this
    ** config = ServletConfig
    ** all session/app beans declared in globals.jsa
    */
    PageContext pageContext = JspFactory.getDefaultFactory().getPageContext( this, request, response, null, true, JspWriter.DEFAULT_BUFFER, true);
    // Note: this is not emitted if the session directive == false
    HttpSession session = pageContext.getSession();
    int __jsp_tag_starteval;
    ServletContext application = pageContext.getServletContext();
    JspWriter out = pageContext.getOut();
    _validate page = this;
    ServletConfig config = pageContext.getServletConfig();
    javax.servlet.jsp.el.VariableResolver __ojsp_varRes = (VariableResolver)new OracleVariableResolverImpl(pageContext);

    try {


      out.write( "<f:view>");
      out.write( "<h:form" + " binding=\"" + "#{validateBean.form1}"+ "\"" + " id=\"" + "form1"+ "\"" +">");
      out.write( "<h:outputText"+ " value=\"" + "Name Validation Service"+ "\"" + " style=\"" + "font-size:xx-large;"+ "\"" +"/>");
      {
        org.richfaces.taglib.TabPanelTag __jsp_taghandler_1=(org.richfaces.taglib.TabPanelTag)OracleJspRuntime.getTagHandler(pageContext,org.richfaces.taglib.TabPanelTag.class,"org.richfaces.taglib.TabPanelTag id");
        __jsp_taghandler_1.setParent(null);
        __jsp_taghandler_1.setId("UpperPanel");
        __jsp_tag_starteval=__jsp_taghandler_1.doStartTag();
        if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
        {
          {
            org.richfaces.taglib.TabTag __jsp_taghandler_2=(org.richfaces.taglib.TabTag)OracleJspRuntime.getTagHandler(pageContext,org.richfaces.taglib.TabTag.class,"org.richfaces.taglib.TabTag label");
            __jsp_taghandler_2.setParent(__jsp_taghandler_1);
            __jsp_taghandler_2.setLabel("Input Request");
            __jsp_tag_starteval=__jsp_taghandler_2.doStartTag();
            if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
            {
              out.write( "<h:panelGrid" + " columns=\"" + "3"+ "\"" + " columnClasses=\"" + "gridContent"+ "\"" + " id=\"" + "pg"+ "\"" +">");
              out.write( "<h:outputText"+ " value=\"" + "Name"+ "\"" +"/>");
              out.write( "<h:inputText"+ " id=\"" + "nameText"+ "\"" + " size=\"" + "100"+ "\"" + " required=\"" + "true"+ "\"" + " binding=\"" + "#{validateBean.nameInputText}"+ "\"" +"/>");
              {
                org.richfaces.taglib.RichMessageTag __jsp_taghandler_3=(org.richfaces.taglib.RichMessageTag)OracleJspRuntime.getTagHandler(pageContext,org.richfaces.taglib.RichMessageTag.class,"org.richfaces.taglib.RichMessageTag for");
                __jsp_taghandler_3.setParent(__jsp_taghandler_2);
                __jsp_taghandler_3.setFor("nameText");
                __jsp_tag_starteval=__jsp_taghandler_3.doStartTag();
                if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                {
                  out.write( "<f:facet" + " name=\"" + "passedMarker"+ "\"" +">");
                  out.write( "<h:graphicImage"+ " value=\"" + "/html/images/passed.gif"+ "\"" +"/>");
                  out.write( "</f:facet>");
                  out.write( "<f:facet" + " name=\"" + "errorMarker"+ "\"" +">");
                  out.write( "<h:graphicImage"+ " value=\"" + "/html/images/error.gif"+ "\"" +"/>");
                  out.write( "</f:facet>");
                }
                if (__jsp_taghandler_3.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                  return;
                OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_3,3);
              }
              out.write( "<h:outputText"+ " value=\"" + "Type"+ "\"" +"/>");
              out.write( "<h:selectOneRadio" + " id=\"" + "radio"+ "\"" + " required=\"" + "true"+ "\"" + " binding=\"" + "#{validateBean.type}"+ "\"" +">");
              out.write( "<f:selectItem"+ " itemValue=\"" + "Primary"+ "\"" + " itemLabel=\"" + "Primary DS"+ "\"" +"/>");
              out.write( "<f:selectItem"+ " itemValue=\"" + "Processed"+ "\"" + " itemLabel=\"" + "Processed DS"+ "\"" +"/>");
              out.write( "<f:selectItem"+ " itemValue=\"" + "Physics"+ "\"" + " itemLabel=\"" + "Physics Group"+ "\"" +"/>");
              out.write( "<f:selectItem"+ " itemValue=\"" + "Tier"+ "\"" + " itemLabel=\"" + "Data Tier"+ "\"" +"/>");
              out.write( "</h:selectOneRadio>");
              {
                org.richfaces.taglib.RichMessageTag __jsp_taghandler_4=(org.richfaces.taglib.RichMessageTag)OracleJspRuntime.getTagHandler(pageContext,org.richfaces.taglib.RichMessageTag.class,"org.richfaces.taglib.RichMessageTag for");
                __jsp_taghandler_4.setParent(__jsp_taghandler_2);
                __jsp_taghandler_4.setFor("radio");
                __jsp_tag_starteval=__jsp_taghandler_4.doStartTag();
                if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                {
                  out.write( "<f:facet" + " name=\"" + "passedMarker"+ "\"" +">");
                  out.write( "<h:graphicImage"+ " value=\"" + "/html/images/passed.gif"+ "\"" +"/>");
                  out.write( "</f:facet>");
                  out.write( "<f:facet" + " name=\"" + "errorMarker"+ "\"" +">");
                  out.write( "<h:graphicImage"+ " value=\"" + "/html/images/error.gif"+ "\"" +"/>");
                  out.write( "</f:facet>");
                }
                if (__jsp_taghandler_4.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                  return;
                OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_4,3);
              }
              out.write( "<f:facet" + " name=\"" + "footer"+ "\"" +">");
              out.write( "<h:commandButton"+ " id=\"" + "submitButton"+ "\"" + " value=\"" + "Validate"+ "\"" + " action=\"" + "#{validateBean.validateAction}"+ "\"" + " reRender=\"" + "outTab"+ "\"" +"/>");
              out.write( "</f:facet>");
              out.write( "</h:panelGrid>");
              {
                org.richfaces.taglib.RichMessageTag __jsp_taghandler_5=(org.richfaces.taglib.RichMessageTag)OracleJspRuntime.getTagHandler(pageContext,org.richfaces.taglib.RichMessageTag.class,"org.richfaces.taglib.RichMessageTag binding for");
                __jsp_taghandler_5.setParent(__jsp_taghandler_2);
                __jsp_taghandler_5.setBinding("#{validateBean.generalInputMessage}");
                __jsp_taghandler_5.setFor("pg");
                __jsp_tag_starteval=__jsp_taghandler_5.doStartTag();
                if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                {
                  out.write( "<f:facet" + " name=\"" + "passedMarker"+ "\"" +">");
                  out.write( "<h:graphicImage"+ " value=\"" + "/html/images/passed.gif"+ "\"" +"/>");
                  out.write( "</f:facet>");
                  out.write( "<f:facet" + " name=\"" + "errorMarker"+ "\"" +">");
                  out.write( "<h:graphicImage"+ " value=\"" + "/html/images/error.gif"+ "\"" +"/>");
                  out.write( "</f:facet>");
                }
                if (__jsp_taghandler_5.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                  return;
                OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_5,3);
              }
            }
            if (__jsp_taghandler_2.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
              return;
            OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_2,2);
          }
        }
        if (__jsp_taghandler_1.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
          return;
        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_1,1);
      }
      {
        org.richfaces.taglib.TabPanelTag __jsp_taghandler_6=(org.richfaces.taglib.TabPanelTag)OracleJspRuntime.getTagHandler(pageContext,org.richfaces.taglib.TabPanelTag.class,"org.richfaces.taglib.TabPanelTag id");
        __jsp_taghandler_6.setParent(null);
        __jsp_taghandler_6.setId("BottomPanel");
        __jsp_tag_starteval=__jsp_taghandler_6.doStartTag();
        if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
        {
          {
            org.richfaces.taglib.TabTag __jsp_taghandler_7=(org.richfaces.taglib.TabTag)OracleJspRuntime.getTagHandler(pageContext,org.richfaces.taglib.TabTag.class,"org.richfaces.taglib.TabTag id label rendered");
            __jsp_taghandler_7.setParent(__jsp_taghandler_6);
            __jsp_taghandler_7.setId("outTab");
            __jsp_taghandler_7.setLabel("Output Request");
            __jsp_taghandler_7.setRendered("#{validateBean.loadTable}");
            __jsp_tag_starteval=__jsp_taghandler_7.doStartTag();
            if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
            {
              {
                org.richfaces.taglib.DataTableTag __jsp_taghandler_8=(org.richfaces.taglib.DataTableTag)OracleJspRuntime.getTagHandler(pageContext,org.richfaces.taglib.DataTableTag.class,"org.richfaces.taglib.DataTableTag columnClasses id rendered rowClasses rows styleClass title value var");
                __jsp_taghandler_8.setParent(__jsp_taghandler_7);
                __jsp_taghandler_8.setColumnClasses("column-index");
                __jsp_taghandler_8.setId("dataTable1");
                __jsp_taghandler_8.setRendered("true");
                __jsp_taghandler_8.setRowClasses("list-row3");
                __jsp_taghandler_8.setRows("20");
                __jsp_taghandler_8.setStyleClass("list-table1");
                __jsp_taghandler_8.setTitle("Similar Names in DBS");
                __jsp_taghandler_8.setValue("#{validateBean.result}");
                __jsp_taghandler_8.setVar("resultVar");
                __jsp_tag_starteval=__jsp_taghandler_8.doStartTag();
                if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                {
                  out.write( "<f:facet" + " name=\"" + "header"+ "\"" +">");
                  {
                    org.richfaces.taglib.ColumnGroupTag __jsp_taghandler_9=(org.richfaces.taglib.ColumnGroupTag)OracleJspRuntime.getTagHandler(pageContext,org.richfaces.taglib.ColumnGroupTag.class,"org.richfaces.taglib.ColumnGroupTag");
                    __jsp_taghandler_9.setParent(__jsp_taghandler_8);
                    __jsp_tag_starteval=__jsp_taghandler_9.doStartTag();
                    if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                    {
                      {
                        org.richfaces.taglib.ColumnTag __jsp_taghandler_10=(org.richfaces.taglib.ColumnTag)OracleJspRuntime.getTagHandler(pageContext,org.richfaces.taglib.ColumnTag.class,"org.richfaces.taglib.ColumnTag");
                        __jsp_taghandler_10.setParent(__jsp_taghandler_9);
                        __jsp_tag_starteval=__jsp_taghandler_10.doStartTag();
                        if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                        {
                          out.write( "<h:outputText"+ " value=\"" + "Names in DBS"+ "\"" +"/>");
                        }
                        if (__jsp_taghandler_10.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                          return;
                        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_10,5);
                      }
                      {
                        org.richfaces.taglib.ColumnTag __jsp_taghandler_11=(org.richfaces.taglib.ColumnTag)OracleJspRuntime.getTagHandler(pageContext,org.richfaces.taglib.ColumnTag.class,"org.richfaces.taglib.ColumnTag");
                        __jsp_taghandler_11.setParent(__jsp_taghandler_9);
                        __jsp_tag_starteval=__jsp_taghandler_11.doStartTag();
                        if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                        {
                          out.write( "<h:outputText"+ " value=\"" + "Similarity in %"+ "\"" +"/>");
                        }
                        if (__jsp_taghandler_11.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                          return;
                        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_11,5);
                      }
                    }
                    if (__jsp_taghandler_9.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                      return;
                    OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_9,4);
                  }
                  out.write( "</f:facet>");
                  {
                    org.richfaces.taglib.ColumnTag __jsp_taghandler_12=(org.richfaces.taglib.ColumnTag)OracleJspRuntime.getTagHandler(pageContext,org.richfaces.taglib.ColumnTag.class,"org.richfaces.taglib.ColumnTag");
                    __jsp_taghandler_12.setParent(__jsp_taghandler_8);
                    __jsp_tag_starteval=__jsp_taghandler_12.doStartTag();
                    if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                    {
                      out.write( "<h:outputText"+ " value=\"" + "#{resultVar.name}"+ "\"" +"/>");
                    }
                    if (__jsp_taghandler_12.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                      return;
                    OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_12,4);
                  }
                  {
                    org.richfaces.taglib.ColumnTag __jsp_taghandler_13=(org.richfaces.taglib.ColumnTag)OracleJspRuntime.getTagHandler(pageContext,org.richfaces.taglib.ColumnTag.class,"org.richfaces.taglib.ColumnTag");
                    __jsp_taghandler_13.setParent(__jsp_taghandler_8);
                    __jsp_tag_starteval=__jsp_taghandler_13.doStartTag();
                    if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                    {
                      out.write( "<h:outputText"+ " value=\"" + "#{resultVar.similar}"+ "\"" +"/>");
                    }
                    if (__jsp_taghandler_13.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                      return;
                    OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_13,4);
                  }
                  out.write( "<f:facet" + " name=\"" + "footer"+ "\"" +">");
                  {
                    org.richfaces.taglib.DatascrollerTag __jsp_taghandler_14=(org.richfaces.taglib.DatascrollerTag)OracleJspRuntime.getTagHandler(pageContext,org.richfaces.taglib.DatascrollerTag.class,"org.richfaces.taglib.DatascrollerTag ajaxSingle for maxPages reRender rendered");
                    __jsp_taghandler_14.setParent(__jsp_taghandler_8);
                    __jsp_taghandler_14.setAjaxSingle("true");
                    __jsp_taghandler_14.setFor("dataTable1");
                    __jsp_taghandler_14.setMaxPages("10");
                    __jsp_taghandler_14.setReRender("dataTable1");
                    __jsp_taghandler_14.setRendered("true");
                    __jsp_tag_starteval=__jsp_taghandler_14.doStartTag();
                    if (__jsp_taghandler_14.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                      return;
                    OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_14,4);
                  }
                  out.write( "</f:facet>");
                }
                if (__jsp_taghandler_8.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                  return;
                OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_8,3);
              }
            }
            if (__jsp_taghandler_7.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
              return;
            OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_7,2);
          }
        }
        if (__jsp_taghandler_6.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
          return;
        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_6,1);
      }
      out.write( "</h:form>");
      out.write( "</f:view>");

    }
    catch (Throwable e) {
      if (!(e instanceof javax.servlet.jsp.SkipPageException)){
        try {
          if (out != null) out.clear();
        }
        catch (Exception clearException) {
        }
        pageContext.handlePageException(e);
      }
    }
    finally {
      OracleJspRuntime.extraHandlePCFinally(pageContext, true);
      JspFactory.getDefaultFactory().releasePageContext(pageContext);
    }

  }
}
