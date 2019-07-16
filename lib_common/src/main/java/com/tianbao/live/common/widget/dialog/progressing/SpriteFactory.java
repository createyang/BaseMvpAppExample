package com.tianbao.live.common.widget.dialog.progressing;


import com.tianbao.live.common.widget.dialog.progressing.sprite.Sprite;
import com.tianbao.live.common.widget.dialog.progressing.style.ChasingDots;
import com.tianbao.live.common.widget.dialog.progressing.style.Circle;
import com.tianbao.live.common.widget.dialog.progressing.style.CubeGrid;
import com.tianbao.live.common.widget.dialog.progressing.style.DoubleBounce;
import com.tianbao.live.common.widget.dialog.progressing.style.FadingCircle;
import com.tianbao.live.common.widget.dialog.progressing.style.FoldingCube;
import com.tianbao.live.common.widget.dialog.progressing.style.MultiplePulse;
import com.tianbao.live.common.widget.dialog.progressing.style.MultiplePulseRing;
import com.tianbao.live.common.widget.dialog.progressing.style.Pulse;
import com.tianbao.live.common.widget.dialog.progressing.style.PulseRing;
import com.tianbao.live.common.widget.dialog.progressing.style.RotatingCircle;
import com.tianbao.live.common.widget.dialog.progressing.style.RotatingPlane;
import com.tianbao.live.common.widget.dialog.progressing.style.ThreeBounce;
import com.tianbao.live.common.widget.dialog.progressing.style.WanderingCubes;
import com.tianbao.live.common.widget.dialog.progressing.style.Wave;

/**
 * @author vondear
 */
public class SpriteFactory {

    public static Sprite create(Style style) {
        Sprite sprite = null;
        switch (style) {
            case ROTATING_PLANE:
                sprite = new RotatingPlane();
                break;
            case DOUBLE_BOUNCE:
                sprite = new DoubleBounce();
                break;
            case WAVE:
                sprite = new Wave();
                break;
            case WANDERING_CUBES:
                sprite = new WanderingCubes();
                break;
            case PULSE:
                sprite = new Pulse();
                break;
            case CHASING_DOTS:
                sprite = new ChasingDots();
                break;
            case THREE_BOUNCE:
                sprite = new ThreeBounce();
                break;
            case CIRCLE:
                sprite = new Circle();
                break;
            case CUBE_GRID:
                sprite = new CubeGrid();
                break;
            case FADING_CIRCLE:
                sprite = new FadingCircle();
                break;
            case FOLDING_CUBE:
                sprite = new FoldingCube();
                break;
            case ROTATING_CIRCLE:
                sprite = new RotatingCircle();
                break;
            case MULTIPLE_PULSE:
                sprite = new MultiplePulse();
                break;
            case PULSE_RING:
                sprite = new PulseRing();
                break;
            case MULTIPLE_PULSE_RING:
                sprite = new MultiplePulseRing();
                break;
            default:
                break;
        }
        return sprite;
    }
}
