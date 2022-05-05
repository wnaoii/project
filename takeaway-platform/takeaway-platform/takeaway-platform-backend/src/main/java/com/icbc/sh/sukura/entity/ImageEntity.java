package com.icbc.sh.sukura.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "image")
@Entity
public class ImageEntity {
    @Id
    @Column
    private String imageId;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "BLOB")
    private byte[] imageBody;
    @Column
    private String imageFileName;
    @Column
    private String imageName;
    private long imageSize;
    @Column
    private String imageType;
}
