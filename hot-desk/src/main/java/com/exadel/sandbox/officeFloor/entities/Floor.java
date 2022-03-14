package com.exadel.sandbox.officeFloor.entities;

import com.exadel.sandbox.attachment.entity.Attachment;
import com.exadel.sandbox.seat.entity.Seat;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Floor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fl_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "of_id")
    @ToString.Exclude
    private Office office;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "at_id")
    @ToString.Exclude
    private Attachment attachment;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "floor")
    @ToString.Exclude
    private List<Seat> seats;

    @Column(name = "fl_num")
    private int floorNum;

    @Column(name = "kitchen_status")
    private String kitchenStatus;

    @Column(name = "meetingroom_status")
    private String meetingRoomStatus;

    @CreatedDate
    @Column(name = "fl_created")
    private LocalDateTime created;

    @LastModifiedDate
    @Column(name = "fl_modified")
    private LocalDateTime modified;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Floor floor = (Floor) o;
        return id != null && Objects.equals(id, floor.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
