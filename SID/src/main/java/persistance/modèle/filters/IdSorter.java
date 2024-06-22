package persistance.modèle.filters;

import persistance.modèle.PDocument;

import java.util.Comparator;

/*
 * Un comparateur de PDocument utilisé pour les trier par ordres croissant d'id
 * D'autre comparateur peuvent être implémenté (titre/auteur/...)
 */
public class IdSorter implements Comparator<PDocument> {
    @Override
    public int compare(PDocument o1, PDocument o2) {
        return o1.getId() - o2.getId();
    }
}
