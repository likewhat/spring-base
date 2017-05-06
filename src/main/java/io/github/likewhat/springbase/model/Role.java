package io.github.likewhat.springbase.model;


import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
public class Role extends AbstractAuditingEntity<Integer> {

    /**
     * The name of role
     */
    @Column(length = 32, nullable = false)
    private String name;

    /**
     * The description of role
     */
    @Column(length = 256)
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
