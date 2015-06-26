
package com.example.safer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="duplicateCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="duplicateName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "duplicateCode",
    "duplicateName"
})
@XmlRootElement(name = "addPassengerFault")
public class AddPassengerFault {

    @XmlElement(required = true)
    protected String duplicateCode;
    @XmlElement(required = true)
    protected String duplicateName;

    /**
     * Gets the value of the duplicateCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDuplicateCode() {
        return duplicateCode;
    }

    /**
     * Sets the value of the duplicateCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDuplicateCode(String value) {
        this.duplicateCode = value;
    }

    /**
     * Gets the value of the duplicateName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDuplicateName() {
        return duplicateName;
    }

    /**
     * Sets the value of the duplicateName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDuplicateName(String value) {
        this.duplicateName = value;
    }

}
