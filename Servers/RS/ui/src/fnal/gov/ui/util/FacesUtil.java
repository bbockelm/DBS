package fnal.gov.ui.util;
import javax.faces.context.FacesContext;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.el.ValueBinding;

public class FacesUtil {
    public static Object getSessionMapValue(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(key);
    }
    public static void setSessionMapValue(String key, Object value) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key, value);
    }
    
    public static Object getApplicationMapValue(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get(key);
    }

    public static void setApplicationMapValue(String key, Object value) {
        FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put(key, value);
    }
    
    
    public static Object resolveExpression(FacesContext ctx,
                                           String expression) {
        Application app = ctx.getApplication();
        ValueBinding bind = app.createValueBinding(expression);
        return bind.getValue(ctx);
    }

    /**
     * Convenience method for resolving a reference to a managed bean by name
     * rather than by expression
     * @param ctx FacesContext
     * @param beanName
     * @return Managed object
     */
    public static Object getManagedBeanValue(FacesContext ctx,
                                             String beanName) {
        StringBuffer buff = new StringBuffer("#{");
        buff.append(beanName);
        buff.append("}");
        return resolveExpression(ctx, buff.toString());
    }
    /**
     * Method for setting a new object into a JSF managed bean
     * Note: will fail silently if the supplied object does
     * not match the type of the managed bean
     * @param ctx FacesContext
     * @param expression
     * @param newValue
     */
    public static void setExpressionValue(FacesContext ctx, String expression,
                                          Object newValue) {
        Application app = ctx.getApplication();
        ValueBinding bind = app.createValueBinding(expression);

        //Check that the input newValue can be cast to the property type
        //expected by the managed bean.
        //If the managed Bean expects a primitive we rely on Auto-Unboxing
        //I could do a more comprehensive check and conversion from the object
        //to the equivilent primitive but life is too short
        Class bindClass = bind.getType(ctx);
        if (bindClass.isPrimitive() || bindClass.isInstance(newValue)) {
            bind.setValue(ctx, newValue);
        }
    }

    /**
     * Convenience method for setting the value of a managed bean by name
     * rather than by expression
     * @param ctx FacesContext
     * @param beanName
     * @param newValue
     */
    public static void setManagedBeanValue(FacesContext ctx, String beanName,
                                           Object newValue) {
        StringBuffer buff = new StringBuffer("#{");
        buff.append(beanName);
        buff.append("}");
        setExpressionValue(ctx, buff.toString(), newValue);
    }


}

