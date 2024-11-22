/**
 * 
 * @author teagan Paulosn
 * @param <E> any class
 * @version 20231304
 * the purpose of this class is to effectively remove replace and return the empty spot with the last index
 */
public class ArrayListReplaceWithLast<E> extends ArrayList<E> {
    /**Removes Last Element and replaces it with current
     * 
     * @param index index being replaced
     * @return return removed element
     */
    public E replaceWithLast(int index){
        E temp =null;
        checkIndex(index, super.size());
        if(super.size()==1)
            return super.remove(index);
        else if(index==super.size()-1)
            temp = super.remove(index);
        else{
            temp = super.get(index);
            super.set(index, super.remove(super.size()-1));
        }
        return temp;
    }
}
