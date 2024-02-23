package org.inar.jira.board.pojo.request.editIssue;

import lombok.Data;

import java.util.List;

@Data
public class Update {
    private List<LabelsItem> labels;
    private List<SummaryItem> summary;





    public Update(List<LabelsItem> labelsItemList, List<SummaryItem> summaryItemList) {
        this.labels = labelsItemList;
        this.summary = summaryItemList;

    }
}