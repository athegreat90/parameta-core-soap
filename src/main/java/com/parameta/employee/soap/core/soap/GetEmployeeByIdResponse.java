//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.12.19 at 11:40:09 PM COT 
//


package com.parameta.employee.soap.core.soap;

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
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="employeeSoap" type="{https://parameta-employee-soap.herokuapp.com/ws/gen}employeeSoap"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "employeeSoap"
})
@XmlRootElement(name = "getEmployeeByIdResponse")
public class GetEmployeeByIdResponse {

    @XmlElement(required = true)
    protected EmployeeSoap employeeSoap;

    /**
     * Gets the value of the employeeSoap property.
     * 
     * @return
     *     possible object is
     *     {@link EmployeeSoap }
     *     
     */
    public EmployeeSoap getEmployeeSoap() {
        return employeeSoap;
    }

    /**
     * Sets the value of the employeeSoap property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeeSoap }
     *     
     */
    public void setEmployeeSoap(EmployeeSoap value) {
        this.employeeSoap = value;
    }

}
