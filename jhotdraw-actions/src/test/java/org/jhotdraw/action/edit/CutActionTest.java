package org.jhotdraw.action.edit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.*;

import static org.mockito.Mockito.*;

class CutActionTest {
    @Mock private JComponent component;
    @Mock private TransferHandler transferHandler;

    private CutAction cutAction;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(component.getTransferHandler()).thenReturn(transferHandler);
        when(component.isEnabled()).thenReturn(true);
        cutAction = new CutAction(component);
    }

    @Test
    void testCutActionWithFocusedComponent() {
        cutAction.actionPerformed(null);
        verify(transferHandler, times(1)).exportToClipboard(eq(component), any(), eq(TransferHandler.MOVE));
    }

    @Test
    void testCutActionWhenComponentIsDisabled() {
        when(component.isEnabled()).thenReturn(false);
        cutAction.actionPerformed(null);
        verify(transferHandler, never()).exportToClipboard(any(), any(), anyInt());
    }

}
