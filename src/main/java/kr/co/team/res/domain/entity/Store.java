package kr.co.team.res.domain.entity;

import kotlin.time.TimeSource;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.TimeZone;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_store")
@DynamicUpdate
public class Store implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_pid")
    private Long id;

    @Column(name="mber_pid")
    private Long mberPid;

    @Column(name = "store_name")
    private String storeName;

    @Column(name = "store_addres")
    private String storeAddres;

    @Column(name = "store_category")
    private String storeCategory;

    @Column(name = "open_time")
    private String openTime;

    @Column(name = "close_time")
    private String closeTime;

    @Column(name = "store_description")
    private Timestamp storeDescription;

}
