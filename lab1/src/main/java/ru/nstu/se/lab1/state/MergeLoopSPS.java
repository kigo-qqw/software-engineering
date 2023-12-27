package ru.nstu.se.lab1.state;

import ru.nstu.se.lab1.state.dto.MainLoopStateDto;
import ru.nstu.se.lab1.state.dto.MergeLoopStateDto;
import ru.nstu.se.lab1.view.SortingProcessView;

public class MergeLoopSPS<T extends Comparable<T>> extends SortingProcessState<T> {
    private final MergeLoopStateDto<T> mergeLoopStateDto;

    public MergeLoopSPS(SortingProcessView<T> sortingProcessView, MergeLoopStateDto<T> mergeLoopStateDto) {
        super(sortingProcessView);
        this.mergeLoopStateDto = mergeLoopStateDto;
    }

    @Override
    public SortingProcessState<T> next() {
        var groupSize = mergeLoopStateDto.getGlobalStateDto().getGroupSize();
        var leftBlockSize = mergeLoopStateDto.getGlobalStateDto().getLeftBlockSize();
        var rightBlockSize = mergeLoopStateDto.getGlobalStateDto().getRightBlockSize();

        var leftBlockIterator = mergeLoopStateDto.getLeftBlockIterator();
        var rightBlockIterator = mergeLoopStateDto.getRightBlockIterator();
        var k = mergeLoopStateDto.getK();

        getSortingProcessView().clearGroupBorders();
        getSortingProcessView().drawGroupBorders(mergeLoopStateDto.getIterator(), k, mergeLoopStateDto.getGlobalStateDto().getGroupSize());
        if (mergeLoopStateDto.getIterator() >= mergeLoopStateDto.getGlobalStateDto().getArray().size()) {
            mergeLoopStateDto.getGlobalStateDto().setGroupSize(mergeLoopStateDto.getGlobalStateDto().getGroupSize() * 2);
            return new MainLoopSPS<>(getSortingProcessView(), new MainLoopStateDto<>(mergeLoopStateDto.getGlobalStateDto()));
        }

        if (leftBlockIterator == groupSize && rightBlockIterator == groupSize) {
            mergeLoopStateDto.setK(mergeLoopStateDto.getK() + groupSize);
            mergeLoopStateDto.setLeftBlockIterator(0);
            mergeLoopStateDto.setRightBlockIterator(0);

            k = mergeLoopStateDto.getK();
            leftBlockIterator = 0;
            rightBlockIterator = 0;

            getSortingProcessView().clearGroupBorders();
            getSortingProcessView().drawGroupBorders(mergeLoopStateDto.getIterator(), k, mergeLoopStateDto.getGlobalStateDto().getGroupSize());        }

        if (leftBlockIterator == groupSize || k + leftBlockIterator == leftBlockSize) {
            return new MoveFromRightBlockSPS<>(getSortingProcessView(), mergeLoopStateDto);
        } else if (rightBlockIterator == groupSize || k + rightBlockIterator == rightBlockSize) {
            return new MoveFromLeftBlockSPS<>(getSortingProcessView(), mergeLoopStateDto);
        } else {
            return new CompareSPS<>(getSortingProcessView(), mergeLoopStateDto);
        }
    }
}
