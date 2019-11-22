import { FSM } from "./FSM";
import * as transfo from "./transfo";

function multiTouch(element: HTMLElement) : void {
    let Id1 : number, element1 : SVGPoint, parent1 : SVGPoint,
        Id2 : number, element2 : SVGPoint, parent2 : SVGPoint,
        originalMatrix : SVGMatrix,
        getRelevantDataFromEvent = (evt : TouchEvent) : Touch => {
            for(let i=0; i<evt.changedTouches.length; i++) {
                let touch = evt.changedTouches.item(i);
                if(touch.identifier === Id1 || touch.identifier === Id2) {
                    return touch;
                }
            }
            return null;
        };
    enum MT_STATES {Inactive, Translating, Rotozooming}
    let fsm = FSM.parse<MT_STATES>( {
        initialState: MT_STATES.Inactive,
        states: [MT_STATES.Inactive, MT_STATES.Translating, MT_STATES.Rotozooming],
        transitions : [
            { from: MT_STATES.Inactive, to: MT_STATES.Translating,
                eventTargets: [element],
                eventName: ["touchstart"],
                useCapture: false,
                action: (evt : TouchEvent) : boolean => {
                    Id1 = evt.changedTouches.item(0).identifier;
                    const touch = getRelevantDataFromEvent(evt);
                    originalMatrix = transfo.getMatrixFromElement(element);
                    element1 = transfo.getPoint(touch.clientX, touch.clientY).matrixTransform(originalMatrix.inverse());
                    return true;
                }
            },
            { from: MT_STATES.Translating, to: MT_STATES.Translating,
                eventTargets: [document],
                eventName: ["touchmove"],
                useCapture: true,
                action: (evt : TouchEvent) : boolean => {
                    evt.preventDefault();
                    evt.stopPropagation();
                    const touch = getRelevantDataFromEvent(evt);
                    parent1 = transfo.getPoint(touch.clientX, touch.clientY);
                    transfo.drag(element, originalMatrix, element1, parent1);
                    originalMatrix = transfo.getMatrixFromElement(element);
                    return true;
                }
            },
            { from: MT_STATES.Translating,
                to: MT_STATES.Inactive,
                eventTargets: [document],
                eventName: ["touchend"],
                useCapture: true,
                action: (evt : TouchEvent) : boolean => {
                    const touch = getRelevantDataFromEvent(evt);
                    if(touch.identifier === Id1) {
                        Id1 = null;
                        originalMatrix = null;
                        element1 = null;
                        parent1 = null;
                    }
                    return true;
                }
            },
            { from: MT_STATES.Translating, to: MT_STATES.Rotozooming,
                eventTargets: [element],
                eventName: ["touchstart"],
                useCapture: false,
                action: (evt : TouchEvent) : boolean => {
                    Id2 = evt.changedTouches.item(0).identifier;
                    const touch = getRelevantDataFromEvent(evt);
                    parent2 = transfo.getPoint(touch.clientX, touch.clientY);
                    element2 = parent2.matrixTransform(originalMatrix.inverse());
                    return true;
                }
            },
            { from: MT_STATES.Rotozooming, to: MT_STATES.Rotozooming,
                eventTargets: [document],
                eventName: ["touchmove"],
                useCapture: true,
                action: (evt : TouchEvent) : boolean => {
                    evt.preventDefault();
                    evt.stopPropagation();
                    const touch = getRelevantDataFromEvent(evt);
                    if(touch.identifier === Id1) {
                        parent1 = transfo.getPoint(touch.clientX, touch.clientY);
                    } else {
                        parent2 = transfo.getPoint(touch.clientX, touch.clientY);
                    }
                    transfo.rotozoom(element, originalMatrix, element1, parent1, element2, parent2);
                    originalMatrix = transfo.getMatrixFromElement(element);
                    return true;
                }
            },
            { from: MT_STATES.Rotozooming,
                to: MT_STATES.Translating,
                eventTargets: [document],
                eventName: ["touchend"],
                useCapture: true,
                action: (evt : TouchEvent) : boolean => {
                    const touch = getRelevantDataFromEvent(evt);
                    if(touch.identifier === Id1) {
                        Id1 = Id2;
                        element1 = element2;
                        parent1 = parent2;
                    }
                    Id2 = null;
                    element2 = null;
                    parent2 = null;
                    return true;
                }
            }
        ]
    } );
    fsm.start();
}

//______________________________________________________________________________________________________________________
//______________________________________________________________________________________________________________________
//______________________________________________________________________________________________________________________
function isString(s : any) : boolean {
    return typeof(s) === "string" || s instanceof String;
}

export let $ = (sel : string | Element | Element[]) : void => {
    let L : Element[] = [];
    if( isString(sel) ) {
        L = Array.from( document.querySelectorAll(<string>sel) );
    } else if(sel instanceof Element) {
        L.push( sel );
    } else if(sel instanceof Array) {
        L = sel;
    }
    L.forEach( multiTouch );
};
