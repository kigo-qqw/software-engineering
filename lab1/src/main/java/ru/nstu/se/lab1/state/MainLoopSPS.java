package ru.nstu.se.lab1.state;

import ru.nstu.se.lab1.state.dto.MainLoopStateDto;
import ru.nstu.se.lab1.state.dto.MoveToBlockStateDto;
import ru.nstu.se.lab1.view.SortingProcessView;

import java.util.Collections;

public class MainLoopSPS<T extends Comparable<T>> extends SortingProcessState<T> {
    private final MainLoopStateDto<T> mainLoopStateDto;

    public MainLoopSPS(SortingProcessView<T> sortingProcessView, MainLoopStateDto<T> mainLoopStateDto) {
        super(sortingProcessView);
        this.mainLoopStateDto = mainLoopStateDto;
    }

    @Override
    public SortingProcessState<T> next() {
        var globalStateDto = mainLoopStateDto.getGlobalStateDto();
        int nn = globalStateDto.getArray().size() / globalStateDto.getGroupSize();
        if (globalStateDto.getArray().size() % globalStateDto.getGroupSize() != 0) nn++;
        globalStateDto.setLeftBlockSize(nn / 2 * globalStateDto.getGroupSize());
        globalStateDto.setRightBlockSize(globalStateDto.getArray().size() - globalStateDto.getLeftBlockSize());

        getSortingProcessView().clearGroupBorders();

        if (globalStateDto.getLeftBlockSize() <= 0 || globalStateDto.getRightBlockSize() <= 0)
            return new FinishSPS<>(getSortingProcessView());

        globalStateDto.getLeftBlock().ensureCapacity(globalStateDto.getLeftBlockSize());
        globalStateDto.getRightBlock().ensureCapacity(globalStateDto.getRightBlockSize());

        globalStateDto.getLeftBlock().clear();
        globalStateDto.getLeftBlock().addAll(Collections.nCopies(globalStateDto.getLeftBlockSize(), null));
        globalStateDto.getRightBlock().clear();
        globalStateDto.getRightBlock().addAll(Collections.nCopies(globalStateDto.getRightBlockSize(), null));

        getSortingProcessView().setLeftBlock(globalStateDto.getLeftBlock());
        getSortingProcessView().setRightBlock(globalStateDto.getRightBlock());

        var moveToBlockStateDto = new MoveToBlockStateDto<>(globalStateDto, 0);
        return new MoveToLeftBlockSPS<>(getSortingProcessView(), moveToBlockStateDto);
    }
}
