/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aan.girsang.pembukuan.model.master;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author ifnu
 */
@Entity
public class RunningNumber implements Serializable{

    @Id
    private String id;

    private Integer number;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }


}
