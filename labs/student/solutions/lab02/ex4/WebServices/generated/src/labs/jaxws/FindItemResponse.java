
package labs.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "findItemResponse", namespace = "http://labs/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findItemResponse", namespace = "http://labs/")
public class FindItemResponse {

    @XmlElement(name = "return", namespace = "")
    private Model.Item _return;

    /**
     * 
     * @return
     *     returns Item
     */
    public Model.Item getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(Model.Item _return) {
        this._return = _return;
    }

}
