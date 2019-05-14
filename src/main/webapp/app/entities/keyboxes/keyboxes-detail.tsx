import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './keyboxes.reducer';
import { IKeyboxes } from 'app/shared/model/keyboxes.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IKeyboxesDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class KeyboxesDetail extends React.Component<IKeyboxesDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { keyboxesEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="keyboxApp.keyboxes.detail.title">Keyboxes</Translate> [<b>{keyboxesEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="name">
                <Translate contentKey="keyboxApp.keyboxes.name">Name</Translate>
              </span>
            </dt>
            <dd>{keyboxesEntity.name}</dd>
            <dt>
              <span id="login">
                <Translate contentKey="keyboxApp.keyboxes.login">Login</Translate>
              </span>
            </dt>
            <dd>{keyboxesEntity.login}</dd>
            <dt>
              <span id="passwordtext">
                <Translate contentKey="keyboxApp.keyboxes.passwordtext">Passwordtext</Translate>
              </span>
            </dt>
            <dd>{keyboxesEntity.passwordtext}</dd>
            <dt>
              <span id="levelpasswordtext">
                <Translate contentKey="keyboxApp.keyboxes.levelpasswordtext">Levelpasswordtext</Translate>
              </span>
            </dt>
            <dd>{keyboxesEntity.levelpasswordtext}</dd>
            <dt>
              <span id="address">
                <Translate contentKey="keyboxApp.keyboxes.address">Address</Translate>
              </span>
            </dt>
            <dd>{keyboxesEntity.address}</dd>
            <dt>
              <span id="createDate">
                <Translate contentKey="keyboxApp.keyboxes.createDate">Create Date</Translate>
              </span>
            </dt>
            <dd>{keyboxesEntity.createDate}</dd>
            <dt>
              <span id="other">
                <Translate contentKey="keyboxApp.keyboxes.other">Other</Translate>
              </span>
            </dt>
            <dd>{keyboxesEntity.other}</dd>
            <dt>
              <Translate contentKey="keyboxApp.keyboxes.usertoken">Usertoken</Translate>
            </dt>
            <dd>{keyboxesEntity.usertokenId ? keyboxesEntity.usertokenId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/keyboxes" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/keyboxes/${keyboxesEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ keyboxes }: IRootState) => ({
  keyboxesEntity: keyboxes.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(KeyboxesDetail);
