package moe.imoli.mbot.cloud.warframe.data.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.dromara.autotable.annotation.AutoTable;
import org.dromara.mpe.autotable.annotation.Column;
import org.dromara.mpe.autotable.annotation.ColumnId;
import org.dromara.mpe.autotable.annotation.Table;
import org.dromara.mpe.processer.annotation.AutoMapper;
import org.dromara.mpe.processer.annotation.AutoRepository;

@Data
@Table
@AutoTable
@AutoMapper
@AutoRepository
@TableName("wf_nodes")
public class WarframeNode {

    @ColumnId
    @TableId(type = IdType.AUTO)
    public int id;
    @Column
    @TableField
    public String uniqueName;
    @Column
    @TableField
    public String name;
    @Column
    @TableField
    public String enemy;
    @Column
    @TableField
    public String type;
}
